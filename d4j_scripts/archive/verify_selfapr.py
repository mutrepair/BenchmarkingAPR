import os, sys
from os.path import *
import csv
sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import all_projects
from utils import *
from token_utils import *

def extract_op(op_map, proj, mutants_log_path):
    with open (mutants_log_path) as f:
        lines = f.readlines()
        for line in lines:
            items = line.strip().split(':')
            bug_id = proj + '_' + items[0]
            operator = items[1]
            op_map[bug_id] = operator

def patch_identical(patch1, patch2):
    return str_to_raw_str(patch1) == str_to_raw_str(patch2)

def verify(result_csv_path, sampled_ids:list=None):
    result_map = dict()
    counter = 0
    with open(result_csv_path) as f:
        reader = csv.reader(f, delimiter='\t')
        for row in reader:
            counter += 1
            if counter % 100 >= 10: continue
            full_id, line_no, remove_line_count, buggy_file_path, tool_patch, correct_patch = row
            tool_patch = tool_patch.strip()
            correct_patch = correct_patch[7:].strip()
            bug_id = '_'.join(full_id.split('_')[0:2])
            if sampled_ids is not None and bug_id not in sampled_ids: continue
            if bug_id not in result_map.keys():
                result_map[bug_id] = dict()
                result_map[bug_id]['fixed'] = False
            if patch_identical(tool_patch, correct_patch):
                result_map[bug_id]['fixed'] = True
    for bug_id in result_map.keys():
        proj, mutant_id = bug_id.split('_')
        if result_map[bug_id]['fixed']:
            fix_count[proj] = fix_count.get(proj, 0) + 1
            op = op_map[bug_id]
            op_count[op] = op_count.get(op, 0) + 1

if __name__ == '__main__':
    d4j_projs = all_projects
    mode = sys.argv[1]
    redundant_patch_map = dict()
    lazy_patch_map = dict()
    mutants_root_dir = '/home/jun/research/dlapr/all_mutants'
    projects = d4j_projs

    if mode == 'd4j':
        result_csv_path = '/home/jun/research/dlapr/SelfAPR/raw_results_1700.csv'
    elif mode == 'd4j_add':
        result_csv_path = '/home/jun/research/dlapr/SelfAPR/raw_results_700.csv'
    else:
        sys.exit('invalid argument')
    
    op_count = {}
    op_map = {}
    fix_count = {}
    mutants = {}
    for proj in projects:
        mutants_log_path = join(mutants_root_dir, proj + '-1f', 'mutants.log')
        extract_op(op_map, proj, mutants_log_path)
        
    verify(result_csv_path)
    print(op_count)
    print(fix_count)
    # print(sum(int(x) for x in op_count.values()))