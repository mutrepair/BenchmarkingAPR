import os, sys

sys.path.append(os.path.abspath(__file__ + '/../../'))
from os.path import *
from utils import *
from defects4j import *
from d4j_info import load_single_line_bug_ids
from d4j_info import get_oracle_patch_line
from utils import get_config_dir, rela_to_abs_path
from config_loader import load_config

def extract_input_id_map(input_file_path:str):
    input_id_map = {}
    with open(input_file_path, 'r') as f:
        lines = f.readlines()[1:]
    for line in lines:
        global_id, bug_id = line.strip().split('\t')[:2]
        input_id_map[global_id] = bug_id
    return input_id_map

if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    reward_repair_config = load_config(join(get_config_dir(), 'reward_repair', 'd4j_ori.yaml'))
    
    bug_id_list = load_single_line_bug_ids()
    buggy_repo_root_dir = rela_to_abs_path(common_config['d4j_buggy_repo_root_dir'])
    d4j_ori_result_file_path = rela_to_abs_path(reward_repair_config['output_csv_path'])
    d4j_bug_list_path = rela_to_abs_path(reward_repair_config['input_csv_path'])
    patch_root_dir = rela_to_abs_path(reward_repair_config['src_patch_dir'])
    bug_id_map = extract_input_id_map(d4j_bug_list_path)
    for bug_id in bug_id_list:
        proj_name, id = bug_id.split('_')
        proj_dir = join(buggy_repo_root_dir, bug_id)
        d4j_proj = Defects4J(proj_name, id, 'buggy', proj_home=join(proj_dir))
        d4j_proj.checkout_if_not_exist_or_empty()
        d4j_proj.clean()
        ori_src_file_path = d4j_proj.get_buggy_file_path()
        buggy_line_no = get_oracle_patch_line(bug_id)[0]
        with open(ori_src_file_path, 'r') as f:
            src_lines = f.readlines()
        with open(d4j_ori_result_file_path, 'r') as f:
            lines = f.readlines()
        counter = 0
        for line in lines:
            global_id = line.strip().split('\t')[0]
            patch = line[len(global_id):].strip()
            if bug_id_map[global_id] == bug_id.replace('_', ''):
                src_lines[buggy_line_no - 1] = patch + '\n'
                counter += 1
                patch_output_file = mkdir_for_file(join(patch_root_dir, proj_name, id, str(counter) + '.java'))
                with open(patch_output_file, 'w') as f:
                    f.writelines(src_lines)