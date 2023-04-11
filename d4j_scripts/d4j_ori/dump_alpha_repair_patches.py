import os,sys
from os.path import *
from json import load
sys.path.append(os.path.abspath(__file__ + '/../../'))
from defects4j import Defects4J
from utils import get_config_dir, rela_to_abs_path
from config_loader import load_config

def parse_bug_info(dataset_json_path:str, result:dict):
    with open(dataset_json_path) as f:
        dataset = load(f)
        for bug_id in dataset:
            if bug_id in ['closure_102', 'jsoup_49', 'time_7', 'jsoup_64', 'closure_13']: continue
            assert len(dataset[bug_id]) == 1, bug_id
            bug_info = dataset[bug_id][0]
            start = bug_info['start']
            end = bug_info['end']
            result[bug_id] = (start, end)
            
def extract_context(file_path:str, start:int, end:int):
    with open(file_path) as f:
        lines = f.readlines()
    return lines[:start], lines[end + 1:]

if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    alpha_repair_config = load_config(join(get_config_dir(), 'alpha_repair', 'd4j_ori.yaml'))
    
    bug_info_dict = {}
    alpha_repair_result_dir = rela_to_abs_path(alpha_repair_config['result_dir'])
    target_root_dir = rela_to_abs_path(alpha_repair_config['src_patch_dir'])
    bug_info_path = rela_to_abs_path(alpha_repair_config['bug_info_path'])
    parse_bug_info(bug_info_path, bug_info_dict)
    d4j_buggy_root_dir = rela_to_abs_path(common_config['d4j_buggy_repo_root_dir'])
    for bug_id in bug_info_dict:
        if bug_id in ['closure_102', 'jsoup_49', 'time_7', 'jsoup_64', 'closure_13']: continue # cannot handle
        start, end = bug_info_dict[bug_id]
        proj, id = bug_id.split('_')
        target_dir = join(target_root_dir, proj, id)
        d4j_proj = Defects4J(proj, id, 'buggy', proj_home=join(d4j_buggy_root_dir, bug_id))
        d4j_proj.checkout_if_not_exist_or_empty()
        ori_src_file_path = d4j_proj.get_buggy_file_path()
        assert isfile(ori_src_file_path), ori_src_file_path
        
        pre_context, post_context = extract_context(ori_src_file_path, start, end)
        
        for file in os.listdir(alpha_repair_result_dir):
            if (file.startswith('_'.join([proj, id]) + '-') and file.endswith('.java')) \
                or (file.startswith('-'.join([proj, id]) + '-') and file.endswith('.java')):
                    patch_id = file.split('_')[-1].split('.')[0]
                    if int(patch_id) >= 100:
                        continue
                    with open(join(alpha_repair_result_dir, file)) as f:
                        patch_mid_lines = f.readlines()
                    target_file_path = join(target_dir, patch_id + '.java')    
                    os.makedirs(target_dir, exist_ok=True)
                    patch_lines = pre_context + patch_mid_lines + ['\n'] + post_context
                    with open(target_file_path, 'w') as f:
                        f.writelines(patch_lines)