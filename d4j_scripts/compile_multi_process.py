import os,sys
import shutil

from major_report_parser import load_mutants
sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import Defects4J, all_projects, add_projects
from os.path import *
from utils import *
from enum import Enum

from pathlib import Path
from generate_bytecode_patch import *

from config_loader import load_config

processPool = []  # storing (cmd, cwd, process, logFileObj, dirSlotId, processName, fileToBeReplacedPath)
maxMultiProcess = 4

dirSlot = [ 0 ] * maxMultiProcess  # 0 means available, 1 means busy

def runCmdAndWaitForFinish(cmd: list, cwd=None):
    process = subprocess.Popen(cmd, shell=False, stdout=subprocess.PIPE, stderr=subprocess.PIPE, cwd=cwd, universal_newlines=True)
    stdout, stderr = process.communicate()
    retCode = process.poll()
    return stdout, stderr, retCode

def checkHaveFailedTest(cwd: str):
    path = Path(cwd) / 'failing_tests'
    if not path.exists():
        return False
    with path.open() as f:
        for line in f:
            if line.startswith('--- '):
                return True
    return False

def waitUntilMultiProcessLessThan(t):
    if len(processPool) >= t:
        print("[INFO] Waiting the number of parallel processes become less than " + str(t))
    while len(processPool) >= t:
        for element in processPool:
            cmd, cwd, process, logFileObj, dirSlotId, processName, fileToBeReplacedPath, buildRelativePath, rela_file_src_path = element
            retCode = process.poll()
            # haven't finished yet
            if retCode is None:
                continue
            else:
                bin_dir_path = join(cwd, buildRelativePath)
                print('=' * 10 + ' `{}` in "{}" Finished '.format(cmd, cwd) + '=' * 10)
                print('=' * 10 + ' {} retCode: {} '.format(processName, retCode) + '=' * 10)
                projName, mid, patchId = processName.split('-')
                print('process {} return code: {}'.format(processName, retCode))
                print('using slot: ' + str(dirSlotId))
                print('current bin dir: ' + bin_dir_path)
                assert isdir(bin_dir_path), 'bin dir not exist: {}'.format(bin_dir_path)
                if retCode == 124:
                    compile_error_msg = "compile timeout for mutant " + processName
                    print(compile_error_msg)
                    with open(join(validation_result_root_dir, tool, projName + '-1f', mid, patchId + '.txt'), 'w') as f:
                        f.write(ValidateStatus.COMPILE_TIMEOUT.name)
                elif retCode != 0:
                    compile_error_msg = "compile error for mutant " + processName
                    print(compile_error_msg)
                    with open(join(validation_result_root_dir, tool, projName + '-1f', mid, patchId + '.txt'), 'w') as f:
                        f.write(ValidateStatus.COMPILE_ERROR.name)
                else:
                    # copy the class as well as its nested classes
                    bytecode_files = get_bytecode_files(bin_dir_path, rela_file_src_path)
                    store_bytecode_files(bytecode_files, bin_dir_path, join(patch_target_dir, projName, mid, patchId))
                os.system('rm -rf ' + bin_dir_path)
                processPool.remove(element)
                dirSlot[dirSlotId] = 0
                # recover replaced file
                shutil.copyfile(str(fileToBeReplacedPath) + '.bak', str(fileToBeReplacedPath))
                if logFileObj is not None:
                    logFileObj.close()
                print('Found {} process in pool running'.format(len(processPool)))
                break

def waitForProcessPoolSlotAvailable():
    waitUntilMultiProcessLessThan(maxMultiProcess)

def waitForProcessPoolFinish():
    waitUntilMultiProcessLessThan(1)

def tryD4jCompile(slotDirPathPrefix:str, mid:str, srcRelativePath:str, buildRelativePath:str, logPath:str, processName: str, patch_path, rela_file_src_path):
    if len(processPool) >= maxMultiProcess:
        waitForProcessPoolSlotAvailable()

    # select dir slot
    assert min(dirSlot) == 0
    for idx, value in enumerate(dirSlot):
        if value == 0:
            dirSlotId = idx
            break
    
    targetDir = slotDirPathPrefix + str(dirSlotId)

    projectName, mid, patchId = processName.split('-')
    d4jProj = Defects4J(projectName, '13' if projectName == 'mockito' else '25' if projectName == 'collections' else '1', 'fixed', targetDir)

    # apply mutant!
    target_file_path = join(targetDir, srcRelativePath, rela_file_src_path)
    shutil.copyfile(target_file_path, target_file_path + '.bak')
    d4jProj.apply_mutant_file(patch_path, rela_file_src_path, srcRelativePath)

    logFileObj = open(logPath, 'w')
    cmd = 'timeout 300 defects4j compile'.split()
    process = subprocess.Popen(cmd, shell=False, stdout=logFileObj, stderr=logFileObj, cwd=targetDir, universal_newlines=True)  # shell=False by default
    dirSlot[dirSlotId] = 1
    cwd = targetDir
    processPool.append((cmd, cwd, process, logFileObj, dirSlotId, processName, target_file_path, buildRelativePath, rela_file_src_path))
    print("Process {}: \"{}\"@\"{}\" has started and been added to the pool, using slot {}.".format(processName, cmd, cwd, dirSlotId))
    return True

# =============================================================================================

if __name__ == '__main__':
    try:
        mode = sys.argv[1]
        tool = sys.argv[2]
        
        common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
        dataset_config = load_config(join(get_config_dir(), 'common', 'dataset.yaml'))
        
        multi_repo_root_dir = rela_to_abs_path(common_config['multi_process_repo_root_dir'])
        ori_repo_root_dir = rela_to_abs_path(common_config['validation_repo_root_dir']) # make sure this repo is already checked out and clean
        mutant_root_dir = rela_to_abs_path(common_config['mutant_root_dir'])
        
        if mode == 'd4j':
            projects = all_projects
            sampled_id_root_dir = rela_to_abs_path(dataset_config['sample_1700_list_dir'])
            patch_root_dir = rela_to_abs_path(join(common_config['mutant_patch_root_dir'], '{}_patches'.format(tool)))
            patch_target_dir = rela_to_abs_path(join(common_config['mutant_bin_patch_root_dir'], '{}_patches'.format(tool)))
            validation_result_root_dir = rela_to_abs_path(join(common_config['mutant_compile_result_root_dir'], tool))
            validation_log_dir_path = rela_to_abs_path(join(common_config['compile_log_root_dir'], tool))
        elif mode == 'd4j_add':
            projects = add_projects
            sampled_id_root_dir = rela_to_abs_path(dataset_config['sample_1700_add_list_dir'])
            patch_root_dir = rela_to_abs_path(join(common_config['mutant_patch_root_dir'], '{}_patches'.format(tool))).replace("src_patches", "src_patches_add")
            patch_target_dir = rela_to_abs_path(join(common_config['mutant_bin_patch_root_dir'], '{}_patches'.format(tool))).replace("bin_patches", "bin_patches_add")
            validation_result_root_dir = rela_to_abs_path(join(common_config['mutant_compile_result_root_dir'], tool)).replace("result", "result_add")
            validation_log_dir_path = rela_to_abs_path(join(common_config['compile_log_root_dir'], tool)).replace("log", "log_add")
        validation_log_dir_path.mkdir(exist_ok=True)

        for project in projects:
            proj_dir = join(multi_repo_root_dir, project + "-0")
            
            # prepare dir slots
            soltDirPrefix = proj_dir[:-1]
            for i in range(maxMultiProcess):
                dir = Path(join(multi_repo_root_dir, project + "-" + str(i)))
                if dir.exists():
                    # shutil.rmtree(str(dir), ignore_errors=True)
                    os.system('rm -rf {}'.format(str(dir)))
            bug_id = '13' if project == 'mockito' else '25' if project == 'collections' else '1'
            d4j_proj = Defects4J(project, bug_id, 'fixed', proj_dir)
            # d4j_proj.checkout_if_not_exist_or_empty()
            shutil.copytree(join(ori_repo_root_dir, project + "-1f"), proj_dir)
            d4j_proj.load_properties()
            
            d4j_proj.clean()
            for i in range(maxMultiProcess):
                if i == 0:
                    continue
                slotDir = join(multi_repo_root_dir, project + "-" + str(i))
                shutil.copytree(proj_dir, slotDir)

            srcRelativePath = d4j_proj.get_rela_src_path()
            src_dir_path = join(proj_dir, srcRelativePath)
            buildRelativePath = d4j_proj.get_rela_bin_path()

            sampled_id_file_path = join(sampled_id_root_dir, project + '-1f', 'sampledMutIds.txt')
            sampled_ids = file_to_ids(sampled_id_file_path)
            mutants = load_mutants(project, mutant_root_dir)
            
            for mutant_id in sampled_ids:
                if (tool == 'recoder' and project == 'jacksondatabind' and mutant_id == '5492') or \
                    (tool == 'recoder' and project == 'lang' and mutant_id == '4586') or \
                        (tool == 'recoder' and project == 'closure' and mutant_id == '48224') or \
                            (tool == 'alpha_repair' and project == 'csv' and mutant_id == '115'):
                    continue
                if (tool in ['recoder', 'sequencer', 'reward_repair'] and project == 'jacksoncore' and mutant_id == '12376'):
                    continue
                mutant = mutants[mutant_id]
                patch_list = extract_patch_path(tool, patch_root_dir, project, mutant_id)
                if patch_list is None or len(patch_list) == 0:
                    print('{}: no patches-pool is found for {}-{}, skipping'.format(tool, project, mutant_id))
                    continue
                for patch_id, patch_path in patch_list:
                    print(patch_id)
                    print(patch_path)
                    if result_exists(project, mutant_id, patch_id, validation_result_root_dir, patch_target_dir, tool):
                        print('result exists for ' + project + '-' + mutant_id + '-' + patch_id)
                        continue
                    rela_file_src_path = mutant['path']
                    result_file_path = mkdir_for_file(join(validation_result_root_dir, tool, project + '-1f', mutant_id, patch_id + '.txt'))

                    (validation_log_dir_path / tool).mkdir(parents=True, exist_ok=True)
                    tryD4jCompile(soltDirPrefix, mutant_id, srcRelativePath, buildRelativePath,\
                        str(validation_log_dir_path / tool / ('{}-{}-{}.log'.format(project, mutant_id, patch_id))),\
                            '{}-{}-{}'.format(project, mutant_id, patch_id),\
                                patch_path, rela_file_src_path)

            waitForProcessPoolFinish()
            for i in range(maxMultiProcess):
                dir = Path(join(multi_repo_root_dir, project + "-" + str(i)))
                if dir.exists():
                    shutil.rmtree(str(dir), ignore_errors=True)

    finally:
        for cmd, cwd, process, logFileObj, dirSlotId, processName, fileToBeReplacedPath, bin_dir_path, rela_file_src_path in processPool:
            logFileObj.close()
            process.kill()
        shutil.rmtree(multi_repo_root_dir, ignore_errors=True)