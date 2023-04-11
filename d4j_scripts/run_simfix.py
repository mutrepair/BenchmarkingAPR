import shutil
import subprocess
import os,sys
sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import Defects4J, all_projects, add_projects
from os.path import *
from major_report_parser import load_mutants
from utils import *
from config_loader import load_config

def prepare_loc_file(class_name, line_no, loc_file_path):
    par_dir_path = dirname(loc_file_path)
    if not isdir(par_dir_path):
        os.makedirs(par_dir_path)
    # assert isfile(loc_file_path), loc_file_path
    print(loc_file_path)
    loc = class_name + '#' + str(line_no) + ',1.0,(-1,-1)'
    print(loc)
    with open(loc_file_path, 'w') as f:
        f.write(loc)

def apply_mutant_patch(proj_name, mutant_id, src_rela_dir, repo_dir):
    mutant_file_path = join(mutant_root_dir, fix_proj_1f(proj_name + '-1f'), 'mutants', mutant_id, mutants[mutant_id]['path'])
    # mutant_file_path = join(mutants_root_dir, proj_name + '-1f', 'mutants_add', mutant_id, mutants[mutant_id]['path'])
    assert isfile(mutant_file_path), mutant_file_path
    ori_file_path = join(repo_dir, src_rela_dir, mutants[mutant_id]['path'])
    assert isfile(ori_file_path), ori_file_path
    shutil.copyfile(mutant_file_path, ori_file_path)

def run_simfix(proj_name, mutant_id, proj_dir):
    bug_id = '13' if proj_name == 'mockito' else '25' if proj_name == 'collections' else '1'
    d4j_proj = Defects4J(proj_name, bug_id, 'fixed', proj_dir)
    if proj_name == 'time' and mutant_id == '20246':
        # this mutant will lead to initialization error, therefore skip it for now
        print('skip time-20246')
        return
    if not isdir(proj_dir):
        d4j_proj.checkout()
    else:
        d4j_proj.clean()
        
    # apply mutant patch and set faulty line
    apply_mutant_patch(proj_name, mutant_id, d4j_proj.get_rela_src_path(), proj_dir)
    mutant = mutants[mutant_id]
    # prepare_loc_file(mutants[mutant_id]['class_name'], mutants[mutant_id]['line_no'], join(loc_root_dir, proj_name, '1.txt'))
    log_path = join(log_root_dir, proj_name, mutant_id + '.log')
    if not isdir(dirname(log_path)):
        os.makedirs(dirname(log_path))
    class_path = ":".join([join(simfix_home, "bin")] + [join(simfix_home, "lib", dir) for dir in os.listdir(join(simfix_home, "lib"))])
    cmd = ["java", "-classpath", class_path, "cofix.main.Main", "--proj_home=" + repo_root_dir, "--proj_name=" + \
        proj_name, "--bug_id=1", "--mutant_id=" + mutant_id, "--oracle_faulty_class=" + mutant['class_name'], \
        "--oracle_faulty_line=" + str(mutant['line_no'])]
    print(' '.join(cmd))
    os.chdir(simfix_home)
    subprocess.call(cmd)
    os.chdir(simfix_home)
    with open(log_path, 'w') as f:
        subprocess.run(cmd, stdout=f, shell=f)

if __name__ == '__main__':
    mode = sys.argv[1]
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    simfix_config = load_config(join(get_config_dir(), 'simfix', 'mutants.yaml'))
    dataset_config = load_config(join(get_config_dir(), 'common', 'dataset.yaml'))
    simfix_home = rela_to_abs_path(simfix_config["simfix_home"])
    repo_root_dir = rela_to_abs_path(simfix_config["repo_root_dir"])
    mutant_root_dir = rela_to_abs_path(common_config["mutant_root_dir"])
    loc_root_dir = join(simfix_home, 'd4j-info/location/ochiai')
    if mode == 'd4j':
        log_root_dir = rela_to_abs_path(simfix_config["log_dir"])
        failing_tests_root_dir = rela_to_abs_path(simfix_config["failing_tests_dir"])
        sample_mutants_id_dir = dataset_config['sample_1700_list_dir']
        projects = all_projects
        result_root_dir = rela_to_abs_path(simfix_config["output_dir"])
    elif mode == 'd4j_add':
        log_root_dir = rela_to_abs_path(simfix_config["log_add_dir"])
        failing_tests_root_dir = rela_to_abs_path(simfix_config["failing_tests_add_dir"])
        sample_mutants_id_dir = dataset_config['sample_1700_add_list_dir']
        projects = add_projects
        result_root_dir = rela_to_abs_path(simfix_config["output_add_dir"])
    else:
        raise ValueError('invalid mode: {}'.format(mode))
    for proj_name in projects:
        proj_dir = join(repo_root_dir, proj_name, proj_name + '_1_buggy')
        mutants = load_mutants(proj_name, mutant_root_dir)
        sample_id_file = join(sample_mutants_id_dir, fix_proj_1f(proj_name + '-1f'), 'sampledMutIds.txt')
        sampled_ids = file_to_ids(sample_id_file)
        
        # run simfix for each sampled mutant
        for mutant_id in sampled_ids:
            result_for_mutant_dir = join(result_root_dir, proj_name, mutant_id)
            if isdir(result_for_mutant_dir) and len(os.listdir(result_for_mutant_dir)) > 0: # skip if already have result
                print("mutant {} result already exists, skip".format(proj_name + mutant_id))
                continue
            else:
                print('Running SimFix for mutant', mutant_id)
                if proj_name == 'closure' and mutant_id == '48813':
                    # this mutant does not have failing tests, therefore skip it for now
                    print('skip closure-48813')
                    continue
                if not mutant_has_failing_tests(proj_name, mutant_id, failing_tests_root_dir):
                    print('mutant ' + mutant_id + ' has no failing tests!')
                    sys.exit(1)
                run_simfix(proj_name, mutant_id, proj_dir)