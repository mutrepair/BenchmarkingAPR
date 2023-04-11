import os, sys
from os.path import *
import csv
sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import all_projects, add_projects
from utils import extract_sampled_bug_id_list_for_mutators

def extract_op(mutants_log_path):
    op_map = {}
    with open (mutants_log_path) as f:
        lines = f.readlines()
    for line in lines:
        items = line.strip().split(':')
        bug_id = proj + items[0]
        op = items[1]
        op_map[bug_id] = op

    return op_map

def verify(proj, patch_root_dir, sampled_ids:list=None):
    if mode == 'd4j_add' and proj not in add_projects: return
    for mutant_id in os.listdir(patch_root_dir):
        if mutant_id.endswith('_old'): continue
        bug_id = proj + mutant_id
        if sampled_ids is not None and bug_id not in sampled_ids: continue
        report_dir = join(proj_result_dir, mutant_id)
        correct_dir = join(report_dir, 'correct')
        compile_err_dir = join(report_dir, 'compilation_failure')
        test_fail_dir = join(report_dir, 'test_failure')
        
        fixed = isdir(correct_dir)
        if fixed: 
            fix_count[proj] = fix_count.get(proj, 0) + 1
            op = op_map[proj + mutant_id]
            op_count[op] = op_count.get(op, 0) + 1
        
        # fix_count_total += fix_count

if __name__ == '__main__':
    sample_by_mutator_ids_dir_path = '/home/jun/research/dlapr/sample_by_mutator_ids'
    
    mode = sys.argv[1]
    if mode == 'd4j':
        bug_id_sample_by_mutator = None
    elif mode == 'd4j_add':
        bug_id_sample_by_mutator = [no_dash.replace('-', '') for no_dash in extract_sampled_bug_id_list_for_mutators(sample_by_mutator_ids_dir_path)]
    else:
        sys.exit('invalid argument')
        
    mutants_root_dir = '/home/jun/research/dlapr/all_mutants'
    op_count = {}
    fix_count = {}
    
    simfix_result_dir = '/home/jun/fastd/dlapr-mirror/apr_patches/simfix'
    simfix_add_result_dir = '/home/jun/fastd/dlapr-mirror/apr_patches/simfix_add'
    for proj in all_projects:
        print('verifying ' + proj)
        mutants = {}
        search_space = 100
        mutants_log_path = join(mutants_root_dir, proj + '-1f', 'mutants.log')

        op_map = extract_op(mutants_log_path)
        proj_result_dir = join(simfix_result_dir, proj + '-1f')
        
        verify(proj, proj_result_dir, bug_id_sample_by_mutator)
        if mode == 'd4j_add':
            proj_result_dir = join(simfix_add_result_dir, proj + '-1f')
            verify(proj, proj_result_dir, bug_id_sample_by_mutator)
    print(op_count)
    print(fix_count)