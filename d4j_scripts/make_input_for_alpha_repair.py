import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../'))
from utils import *
from defects4j import get_fst_v_src_rela_dir, all_projects
from major_report_parser import *
from shutil import copyfile
from config_loader import load_config

if __name__ == '__main__':
    alpha_repair_config = load_config(join(get_config_dir(), 'alpha_repair', 'mutants.yaml'))
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    dataset_config = load_config(join(get_config_dir(), 'common', 'dataset.yaml'))
    repo_root_dir = join(get_root_dir(), common_config['validation_repo_root_dir'])
    mutant_root_dir = join(get_root_dir(), common_config['mutant_root_dir'])
    mode = sys.argv[1]
    
    if mode == 'd4j':
        # mutation benchmark with 1700 bugs
        alpha_repair_input_dir = join(get_root_dir(), alpha_repair_config['alpha_repair_input_dir_1700'])
        print('alpha_repair_input_dir', alpha_repair_input_dir)
        sampled_id_dir = join(get_root_dir(), dataset_config['sample_1700_list_dir'])
        bug_ids = set(extract_sampled_bug_id_list(sampled_id_dir))
    elif mode == 'd4j_add':
        # ~300 bugs to make up the 700 bugs for mutator-wise benchmark
        alpha_repair_input_dir = join(get_root_dir(), alpha_repair_config['alpha_repair_input_dir_add'])
        sampled_id_dir = join(get_root_dir(), dataset_config['sample_1700_add_list_dir'])
        bug_ids = set(extract_sampled_bug_id_list(sampled_id_dir))
    else:
        raise ValueError('mode should be d4j or d4j_add', mode)
        
    mutants = {}
    for proj in all_projects:
        mutants[proj] = load_mutants(proj, mutant_root_dir)
    for bug_id in bug_ids:
        if '_' in bug_id:
            proj, mutant_id = bug_id.split('_')
        else:
            raise ValueError('bug_id should contain _', bug_id)
        fixed_repo_dir = join(repo_root_dir, proj)
        src_rela_dir = get_fst_v_src_rela_dir(proj)
        mutant = mutants[proj][mutant_id]
        mutant_dir = join(mutant_root_dir, fix_proj_1f(proj + '-1f'), 'mutants', mutant_id)
        if not isdir(mutant_dir):
            mutant_dir = join(mutant_root_dir, fix_proj_1f(proj + '-1f'), 'mutants_add', mutant_id)
        assert isdir(mutant_dir), mutant_dir
        fixed_file = join(fixed_repo_dir, src_rela_dir, mutant['path'])
        buggy_file = join(mutant_dir, mutant['path'])
        assert isfile(fixed_file) and isfile(buggy_file)
        
        target_dir = join(alpha_repair_input_dir, bug_id)
        target_buggy_dir = join(target_dir, 'buggy')
        target_fixed_dir = join(target_dir, 'fixed')
        target_diff_dir = join(target_dir, 'diff_buggy_fixed')
        for subdir in [target_buggy_dir, target_fixed_dir, target_diff_dir]:
            os.makedirs(subdir, exist_ok=True)
        copyfile(buggy_file, join(target_buggy_dir, basename(buggy_file)))
        copyfile(fixed_file, join(target_fixed_dir, basename(fixed_file)))
        get_diff(buggy_file, fixed_file, join(target_diff_dir, 'src.patch'), ignore_space=False)
        