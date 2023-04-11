import os, sys

from major_report_parser import load_mutants
sys.path.append(os.path.abspath(__file__ + '/../'))
from os.path import *
from utils import *
from defects4j import *
from config_loader import load_config
from utils import get_config_dir, rela_to_abs_path

def extract_mutant_id_map(sampled_mutant_file_path:str):
    mutant_id_map = {}
    with open(sampled_mutant_file_path, 'r') as f:
        lines = f.readlines()[1:]
    for line in lines:
        global_id, proj_mutant_id = line.strip().split('\t')[:2]
        mutant_id_map[global_id] = proj_mutant_id
    return mutant_id_map

if __name__ == '__main__':
    mode = sys.argv[1]
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    reward_repair_config = load_config(join(get_config_dir(), 'reward_repair', 'mutants.yaml'))
    mutant_root_dir = rela_to_abs_path(common_config['mutant_root_dir'])
    validate_repo_root_dir = rela_to_abs_path(common_config['validation_repo_root_dir'])
    
    if mode == 'd4j':
        sampled_mutants_file_path = rela_to_abs_path(reward_repair_config['input_csv_path'])
        sampled_mutants_patch_result_file_path = rela_to_abs_path(reward_repair_config['output_csv_path'])
        print(sampled_mutants_patch_result_file_path)
        patch_root_dir = rela_to_abs_path(reward_repair_config['src_patch_dir'])
        print(patch_root_dir)
    elif mode == 'd4j_add':
        sampled_mutants_file_path = rela_to_abs_path(reward_repair_config['input_add_csv_path'])
        sampled_mutants_patch_result_file_path = rela_to_abs_path(reward_repair_config['output_add_csv_path'])
        patch_root_dir = rela_to_abs_path(reward_repair_config['src_patch_add_dir'])
    else:
        raise Exception('Unknown mode: {}'.format(mode))
    
    mutant_id_map = extract_mutant_id_map(sampled_mutants_file_path)
    with open(sampled_mutants_patch_result_file_path, 'r') as f:
        lines = f.readlines()
    for proj in all_projects:
        proj_home = join(validate_repo_root_dir, proj)
        bug_id = '13' if proj == 'mockito' else '25' if proj == 'collections' else '1'
        d4j_proj = Defects4J(proj, bug_id, 'fixed', proj_home)
        d4j_proj.checkout_if_not_exist_or_empty()
        d4j_proj.clean()
        src_rela_dir_path = get_fst_v_src_rela_dir(proj)
        mutants = load_mutants(proj, mutant_root_dir)
        count_dict = {}
        for line in lines:
            global_id = line.strip().split('\t')[0]
            patch = line[len(global_id):].strip()
            proj_mutant_id = mutant_id_map[global_id]
            if not proj_mutant_id.startswith(proj):
                continue
            else:
                mutant_id = proj_mutant_id[len(proj):]
                assert mutant_id.isnumeric()
                mutant = mutants[mutant_id]
                rela_path = mutant['path']
                line_no = mutant['line_no']
                src_file_path = join(proj_home, src_rela_dir_path, rela_path)
                assert isfile(src_file_path)
                with open(src_file_path, 'r') as f:
                    src_lines = f.readlines()
                src_lines[line_no - 1] = patch + '\n'
                count_dict[mutant_id] = count_dict.get(mutant_id, 0) + 1
                patch_output_file = mkdir_for_file(join(patch_root_dir, proj, mutant_id, str(count_dict[mutant_id]) + '.java'))
                with open(patch_output_file, 'w') as f:
                    f.writelines(src_lines)