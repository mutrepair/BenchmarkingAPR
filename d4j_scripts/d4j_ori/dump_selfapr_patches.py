import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../../'))
from defects4j import *
from utils import *
from d4j_info import *
from config_loader import load_config

def register_cleaned_set(cleaned_set, bug_id):
    exists = False
    if bug_id in cleaned_set:
        exists = True
    else:
        cleaned_set.add(bug_id)
    return exists

if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    selfapr_config = load_config(join(get_config_dir(), 'selfapr', 'd4j_ori.yaml'))
    
    patch_result_file_path = rela_to_abs_path(selfapr_config['output_csv_path'])
    buggy_root_repo = rela_to_abs_path(common_config['d4j_buggy_repo_root_dir'])
    patch_root_dir = rela_to_abs_path(selfapr_config['src_patch_dir'])
    
    cleaned_set = set()
    
    with open(patch_result_file_path, 'r') as f:
        lines = f.readlines()
    
    counter = 0
    count_dict = {}
    for line in lines:
        counter += 1
        if counter % 100 >= 10: continue
        line = line.strip()
        full_id, line_no, remove_line_count, file_rela_path, tool_patch, correct_patch = line.split('\t')
        tool_patch = tool_patch.strip()
        bug_id = '_'.join(full_id.split('_')[0:2])
        proj_name, id = bug_id.split('_')
        proj_dir = join(buggy_root_repo, bug_id)
        d4j_proj = Defects4J(proj_name, id, 'buggy', proj_dir)
        d4j_proj.checkout_if_not_exist_or_empty()
        if not register_cleaned_set(cleaned_set, bug_id):
            d4j_proj.clean()
        ori_src_file_path = join(proj_dir, d4j_proj.get_buggy_file_path())
        buggy_line_no = get_oracle_patch_line(bug_id)[0]
        assert isfile(ori_src_file_path)
        with open(ori_src_file_path, 'r') as f:
            src_lines = f.readlines()
            src_lines[buggy_line_no - 1] = tool_patch + '\n'
            count_dict[bug_id] = count_dict.get(bug_id, 0) + 1
            patch_output_file = mkdir_for_file(join(patch_root_dir, proj_name, id, str(count_dict[bug_id]) + '.java'))
            with open(patch_output_file, 'w') as f:
                f.writelines(src_lines)
        