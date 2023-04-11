import os, sys
from os.path import *
import csv
from defects4j import all_projects, add_projects
from utils import extract_sampled_bug_id_list_for_mutators
from major_report_parser import load_mutants

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

def verify(proj, prapr_result_dir, sampled_ids:list=None):
    proj_result_dir = join(prapr_result_dir, proj + '-1f')
    for mutant_id in os.listdir(proj_result_dir):
        if sampled_ids is not None and (proj + mutant_id) not in sampled_ids: continue
        report_dir = join(proj_result_dir, mutant_id)
        report_log_path = join(report_dir, 'fix-report.log')
        assert isfile(report_log_path)
        with open(report_log_path) as f:
            lines = f.readlines()
        num_fixes = int(lines[1].strip().split(': ')[1])
        # num_total_patches = int(lines[2].strip().split(': ')[1])
        fixed = num_fixes > 0
        if fixed: 
            fix_count[proj] = fix_count.get(proj, 0) + 1
            op = op_map[proj + mutant_id]
            op_count[op] = op_count.get(op, 0) + 1
            # if op == 'LVR':
            #     print("#" * 20)
            #     print(proj + mutant_id)
            #     mutant = mutants[mutant_id]
            #     print(mutant['change'])

if __name__ == '__main__':
    mode = sys.argv[1]
    if mode == 'd4j':
        projects = all_projects
        sampled_ids = None
    elif mode == 'd4j_add':
        projects = add_projects
        sample_by_mutator_ids_dir_path = '/home/jun/research/dlapr/sample_by_mutator_ids'
        sampled_ids = [no_dash.replace('-', '') for no_dash in extract_sampled_bug_id_list_for_mutators(sample_by_mutator_ids_dir_path)]
    mutants_root_dir = '/home/jun/research/dlapr/all_mutants'
    op_count = {}
    fix_count = {}
    
    prapr_result_dir = '/home/jun/research/dlapr/prapr_result'
    prapr_add_result_dir = '/home/jun/research/dlapr/prapr_result_add'
    for proj in projects:
        print('verifying ' + proj)
        mutants = load_mutants(proj, mutants_root_dir)
        search_space = 100
        mutants_log_path = join(mutants_root_dir, proj + '-1f', 'mutants.log')

        op_map = extract_op(mutants_log_path)
        
        verify(proj, prapr_result_dir, sampled_ids)
        
        if mode == 'd4j_add':
            verify(proj, prapr_add_result_dir, sampled_ids)
    
    print(op_count)
    print(fix_count)