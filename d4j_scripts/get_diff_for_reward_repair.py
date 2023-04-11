import os,sys,subprocess
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../'))
from utils import *
from defects4j import add_projects, get_fst_v_src_rela_dir, all_projects
from config_loader import load_config

def count_file_lines(file):
    return len(file_to_ids(file))

if __name__ == '__main__':
    mode = sys.argv[1]
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    dateset_config = load_config(join(get_config_dir(), 'common', 'dataset.yaml'))
    reward_repair_config = load_config(join(get_config_dir(), 'reward_repair', 'mutants.yaml'))
    d4j_projs = all_projects
    mutant_root_dir = rela_to_abs_path(common_config['mutant_root_dir'])

    if mode == 'd4j':
        projs = d4j_projs
        sampled_id_dir = rela_to_abs_path(dateset_config['sample_1700_list_dir'])
        diff_dir = rela_to_abs_path(reward_repair_config['diff_dir'])
        
    elif mode == 'd4j_add':
        projs = add_projects
        sampled_id_dir = rela_to_abs_path(dateset_config['sample_1700_add_list_dir'])
        diff_dir = rela_to_abs_path(reward_repair_config['diff_add_dir'])
    else:
        raise ValueError('mode should be d4j or d4j_add', mode)
    
    for proj in projs:
        proj_dir = join(mutant_root_dir, fix_proj_1f(proj + '-1f'))
        mutants_dir = join(proj_dir, 'mutants')
        sampled_id_files = join(sampled_id_dir, fix_proj_1f(proj + '-1f'), 'sampledMutIds.txt')
        sampled_mutant_ids = file_to_ids(sampled_id_files)
        if not mode == 'd4j_add': assert len(sampled_mutant_ids) == 100

        for sampled_id in sampled_mutant_ids:
            mutant_dir = join(mutants_dir, sampled_id)
            mutant_file_path, mutant_rel_path = find_mutant_file(mutant_dir)
            ori_file_path = join(proj_dir, get_fst_v_src_rela_dir(proj), mutant_rel_path)
            assert isfile(ori_file_path), ori_file_path
            assert isfile(mutant_file_path), mutant_file_path
            line_num = max(count_file_lines(mutant_file_path), count_file_lines(ori_file_path))
            diff_path = mkdir_for_file(join(diff_dir, proj + sampled_id + '.diff'))
            os.system('diff --unified={} {} {} > {}'.format(line_num, mutant_file_path, ori_file_path, diff_path))