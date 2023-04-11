import os, sys

from major_report_parser import load_mutants
sys.path.append(os.path.abspath(__file__ + '/../'))
from os.path import *
from utils import *
from defects4j import *

from config_loader import load_config

if __name__ == '__main__':
    mode = sys.argv[1]
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    selfapr_config = load_config(join(get_config_dir(), 'selfapr', 'mutants.yaml'))
    mutant_root_dir = rela_to_abs_path(common_config['mutant_root_dir'])
    validate_repo_root_dir = rela_to_abs_path(common_config['validation_repo_root_dir'])
    
    if mode == 'd4j':
        patch_result_file_path = rela_to_abs_path(selfapr_config['output_csv_path'])
        patch_root_dir = rela_to_abs_path(selfapr_config['src_patch_dir'])
    elif mode == 'd4j_add':
        patch_result_file_path = rela_to_abs_path(selfapr_config['output_add_csv_path'])
        patch_root_dir = rela_to_abs_path(selfapr_config['src_patch_add_dir'])
    else:
        raise Exception('Unknown mode: ' + mode)
        
    with open(patch_result_file_path, 'r') as f:
        lines = f.readlines()
    
    mutants = {}
    for proj in all_projects:
        proj_home = join(validate_repo_root_dir, proj)
        bug_id = '13' if proj == 'mockito' else '25' if proj == 'collections' else '1'
        d4j_proj = Defects4J(proj, bug_id, 'fixed', proj_home)
        d4j_proj.checkout_if_not_exist_or_empty()
        d4j_proj.clean()
        src_rela_dir_path = get_fst_v_src_rela_dir(proj)
        mutants[proj] = load_mutants(proj, mutant_root_dir)
        
    counter = 0
    count_dict = {}
    for line in lines:
        counter += 1
        if counter % 100 >= 10: continue
        line = line.strip()
        full_id, line_no, remove_line_count, file_rela_path, tool_patch, correct_patch = line.split('\t')
        tool_patch = tool_patch.strip()
        bug_id = '_'.join(full_id.split('_')[0:2])
        proj, mutant_id = bug_id.split('_')
        ori_src_file_path = join(validate_repo_root_dir, proj, file_rela_path)
        assert isfile(ori_src_file_path)
        mutant = mutants[proj][mutant_id]
        line_no = mutant['line_no']
        with open(ori_src_file_path, 'r') as f:
            src_lines = f.readlines()
            src_lines[line_no - 1] = tool_patch + '\n'
            count_dict[mutant_id] = count_dict.get(mutant_id, 0) + 1
            patch_output_file = mkdir_for_file(join(patch_root_dir, proj, mutant_id, str(count_dict[mutant_id]) + '.java'))
            with open(patch_output_file, 'w') as f:
                f.writelines(src_lines)