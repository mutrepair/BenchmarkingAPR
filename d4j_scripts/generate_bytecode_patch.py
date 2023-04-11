import os,sys
import shutil

from major_report_parser import load_mutants
sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import Defects4J, all_projects
from os.path import *
from utils import *
from enum import Enum

class ValidateStatus(Enum):
    COMPILE_ERROR = 0
    TEST_FAIL = 1
    TEST_PASS = 2
    COMPILE_TIMEOUT = 3

def extract_patch_path(tool:str, patch_root_dir:str, proj:str, mutant_id:str):
    if tool == 'tbar':
        return extract_tbar_patch_path(patch_root_dir, proj, mutant_id)
    elif tool == 'reward_repair' or tool == 'selfapr' or tool == 'alpha_repair':
        return extract_reward_repair_patch_path(patch_root_dir, proj, mutant_id)
    elif tool == 'sequencer':
        return extract_sequencer_patch_path(patch_root_dir, proj, mutant_id)
    elif tool == 'recoder' or tool == 'simfix':
        return extract_recoder_patch_path(patch_root_dir, proj, mutant_id)
    elif tool == 'cure' or tool == 'coconut' or tool == 'tufano' or tool == 'edits':
        return extract_tbar_patch_path(patch_root_dir, proj, mutant_id)
    else:
        raise Exception('unknown tool: ' + tool)
    
def extract_recoder_patch_path(patch_root_dir:str, proj:str, mutant_id:str):
    patch_list = []
    src_patch_dir_path = join(patch_root_dir, proj, mutant_id)
    if not isdir(src_patch_dir_path):
        return patch_list
    for patch in os.listdir(src_patch_dir_path):
        patch_path = join(src_patch_dir_path, patch)
        patch_id = patch.replace('.java', '')
        patch_list.append((patch_id, patch_path))
    return patch_list

def extract_tbar_patch_path(patch_root_dir:str, proj:str, mutant_id:str):
    patch_list = []
    patches_pool_path = join(patch_root_dir, '_'.join([proj, mutant_id]), 'patches-pool')
    if not isdir(patches_pool_path):
        return patch_list
    # assert isdir(patches_pool_path), 'patches pool not found: ' + patches_pool_path
    for patch_id in os.listdir(patches_pool_path):
        patch_dir = join(patches_pool_path, patch_id)
        if not isdir(patch_dir): continue
        file, _ = find_mutant_file(patch_dir)
        patch_list.append((patch_id, file))
    return patch_list

def extract_reward_repair_patch_path(patch_root_dir:str, proj:str, mutant_id:str):
    patch_list = []
    src_patch_dir_path = join(patch_root_dir, proj, mutant_id)
    assert isdir(src_patch_dir_path), 'src patch dir not found: ' + src_patch_dir_path
    for patch in os.listdir(src_patch_dir_path):
        patch_path = join(src_patch_dir_path, patch)
        patch_id = patch.replace('.java', '')
        patch_list.append((patch_id, patch_path))
    return patch_list

def extract_sequencer_patch_path(patch_root_dir:str, proj:str, mutant_id:str):
    patch_list = []
    src_patch_dir_path = join(patch_root_dir, proj + mutant_id)
    assert isdir(src_patch_dir_path), 'src patch dir not found: ' + src_patch_dir_path
    for patch_id in os.listdir(src_patch_dir_path):
        file, _ = find_mutant_file(join(src_patch_dir_path, patch_id))
        patch_list.append((patch_id, file))
    return patch_list

def result_exists(proj:str, mutant_id:str, patch_id:str, result_root_dir:str, patch_target_dir:str, tool:str):
    result_file = join(result_root_dir, tool, proj + '-1f', mutant_id, patch_id + '.txt')
    if isfile(result_file) and getsize(result_file) > 0:
        return True
    bytecode_target_dir = join(patch_target_dir, proj, mutant_id, patch_id)
    if isdir(bytecode_target_dir) and len(os.listdir(bytecode_target_dir)) > 0:
        return True
    return False

def get_bytecode_files(bin_dir_path:str, src_rela_path:str):
    bin_rela_path = src_rela_path.replace('.java', '.class')
    bin_file_path = join(bin_dir_path, bin_rela_path)
    assert isfile(bin_file_path), 'bin file not found: ' + bin_file_path
    class_name = basename(bin_file_path).replace('.class', '')
    bin_file_dir_path = dirname(bin_file_path)
    related_files = [bin_file_path]
    for file in os.listdir(bin_file_dir_path):
        if isfile(join(bin_file_dir_path, file)) and file.startswith(class_name + '$') and file.endswith('.class'):
            related_files.append(join(bin_file_dir_path, file))
    assert len(related_files) > 0, 'no bytecode file found for ' + src_rela_path
    return related_files 

def store_bytecode_files(bin_files:list, bin_dir_path:str, target_dir:str):
    for file in bin_files:
        target_file = join(target_dir, relpath(file, bin_dir_path))
        mkdir_for_file(target_file)
        shutil.copyfile(file, target_file)