import os,sys
from os.path import *
from json import load
sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import all_projects
from major_report_parser import *
from config_loader import load_config
from utils import get_config_dir, fix_proj_1f, rela_to_abs_path

def parse_bug_info(dataset_json_path:str, result:dict):
    with open(dataset_json_path) as f:
        dataset = load(f)
        for bug_id in dataset:
            if bug_id == 'csv_115': continue # cannot handle this bug
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
    alpha_repair_config = load_config(join(get_config_dir(), 'alpha_repair', 'mutants.yaml'))
    mutant_root_dir = rela_to_abs_path(common_config['mutant_root_dir'])
    mode = sys.argv[1]
    bug_info_dict = {}
    
    if mode == 'd4j':
        alpha_repair_result_dir = rela_to_abs_path(alpha_repair_config['result_dir'])
        target_root_dir = rela_to_abs_path(alpha_repair_config['src_patch_dir'])
        bug_info_path = rela_to_abs_path(alpha_repair_config['bug_info_path'])
    elif mode == 'd4j_add':
        alpha_repair_result_dir = rela_to_abs_path(alpha_repair_config['result_add_dir'])
        target_root_dir = rela_to_abs_path(alpha_repair_config['src_patch_add_dir'])
        bug_info_path = rela_to_abs_path(alpha_repair_config['bug_info_add_path'])
    else:
        raise ValueError('mode should be d4j or d4j_add', mode)
    
    parse_bug_info(bug_info_path, bug_info_dict)
    
    all_mutants = {}
    for proj in all_projects:
        all_mutants[proj] = load_mutants(proj, mutant_root_dir)

    for bug_id in bug_info_dict:
        if bug_id == 'csv_115': continue # cannot handle this bug
        start, end = bug_info_dict[bug_id]
        assert '_' in bug_id, bug_id
        proj, mutant_id = bug_id.split('_')
        target_dir = join(target_root_dir, proj, mutant_id)
        mutant = all_mutants[proj][mutant_id]
        ori_file_path = join(mutant_root_dir, fix_proj_1f(proj + '-1f'), 'mutants', mutant_id, mutant['path'])
        if not isfile(ori_file_path):
            ori_file_path = join(mutant_root_dir, fix_proj_1f(proj + '-1f'), 'mutants_add', mutant_id, mutant['path'])
        assert isfile(ori_file_path), ori_file_path
        
        pre_context, post_context = extract_context(ori_file_path, start, end)
        
        for file in os.listdir(alpha_repair_result_dir):
            if (file.startswith('_'.join([proj, mutant_id]) + '-') and file.endswith('.java'))\
                or (file.startswith('-'.join([proj, mutant_id]) + '-') and file.endswith('.java')):
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