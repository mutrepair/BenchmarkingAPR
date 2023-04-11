import os, sys
from os.path import *
import csv
sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import all_projects
from utils import *

def extract_op(mutants_log_path):
    op_map = {}
    with open (mutants_log_path) as f:
        lines = f.readlines()
        for line in lines:
            items = line.strip().split(':')
            bug_id = proj + items[0]
            operator = items[1]
            for seq in mutants.keys():
                if mutants[seq]['bugid'] == bug_id:
                    op_map[seq] = operator
                    break

    return op_map

def patch_identical(patch1, patch2):
    return str_to_raw_str(patch1) == str_to_raw_str(patch2)

def extract_patch(buggy_csv_path):
    with open(buggy_csv_path, newline='') as f:
        reader = csv.reader(f, delimiter='\t')
        next(reader)
        for row in reader:
            seq, bugid, buggy, patch, lineNo, action = row # this seq is the global index of mutants, bug is '$project$id'
            if not bugid.startswith(proj): continue
            mutants[seq] = dict()
            mutants[seq]['bugid'] = bugid
            mutants[seq]['lineNo'] = lineNo
            mutants[seq]['buggy'] = buggy[len('buggy: '):buggy.find('context:  ')].strip()
            mutants[seq]['patch'] = patch[7:]
            mutants[seq]['action'] = action
            mutants[seq]['fixed'] = False
            mutants[seq]['searched'] = 0

def verify(proj, patch_csv_path, sampled_ids:list=None):
    with open(patch_csv_path) as f:
        reader = csv.reader(f, delimiter='\t')
        for row in reader:
            seq, patch = row
            if not seq in mutants.keys(): continue
            if mutants[seq]['searched'] <= search_space:
                mutants[seq]['searched'] += 1
                if patch_identical(patch.strip(), mutants[seq]['patch']):
                    mutants[seq]['fixed'] = True

    for seq in mutants.keys():
        mutant = mutants[seq]
        if sampled_ids is not None and mutant['bugid'] not in sampled_ids: continue
        if mutant['fixed']: 
            fix_count[proj] = fix_count.get(proj, 0) + 1
            op = op_map[seq]
            op_count[op] = op_count.get(op, 0) + 1

if __name__ == '__main__':
    d4j_projs = all_projects
    small_projs = file_to_ids('/home/jun/research/dlapr/small_projects/projects.txt')
    mode = sys.argv[1]
    redundant_patch_map = dict()
    lazy_patch_map = dict()

    if mode == 'small':
        projects = small_projs
        mutants_root_dir = '/home/jun/research/dlapr/small_projects'
        buggy_csv_path = '/home/jun/research/dlapr/RewardRepair/data/sample_mutants/sampled_mutants_small.csv'
        patch_csv_path = '/home/jun/research/dlapr/RewardRepair/small_projs_sampled_mutants_patch_result.csv'
        bug_id_sample_by_mutator = None
    elif mode == 'd4j':
        projects = d4j_projs
        mutants_root_dir = '/home/jun/research/dlapr/all_mutants'
        buggy_csv_path = '/home/jun/research/dlapr/RewardRepair/data/sample_mutants/sampled_mutants_1700.csv'
        patch_csv_path = '/home/jun/research/dlapr/RewardRepair/sampled_mutants_patch_result.csv'
        bug_id_sample_by_mutator = None
    elif mode == 'd4j_add':
        projects = d4j_projs
        mutants_root_dir = '/home/jun/research/dlapr/all_mutants'
        buggy_csv_path = '/home/jun/research/dlapr/RewardRepair/data/sample_mutants/sampled_mutants_1700.csv'
        patch_csv_path = '/home/jun/research/dlapr/RewardRepair/sampled_mutants_patch_result.csv'
        buggy_add_csv_path = '/home/jun/research/dlapr/RewardRepair/data/sample_mutants/sampled_mutants_add.csv'
        patch_add_csv_path = '/home/jun/research/dlapr/RewardRepair/sampled_mutants_patch_add_result.csv'
        sample_by_mutator_ids_dir_path = '/home/jun/research/dlapr/sample_by_mutator_ids'
        bug_id_sample_by_mutator = [no_dash.replace('-', '') for no_dash in extract_sampled_bug_id_list_for_mutators(sample_by_mutator_ids_dir_path)]
    elif mode == 'd4j_std':
        projects = d4j_projs
        mutants_root_dir = '/home/jun/research/dlapr/all_mutants'
        buggy_csv_path = '/home/jun/research/dlapr/RewardRepair/data/sample_mutants/sampled_mutants_std.csv'
        patch_csv_path = '/home/jun/research/dlapr/RewardRepair/sampled_mutants_patch_std_result.csv'
        std_ids_dir_path = '/home/jun/research/dlapr/std_exp'
        bug_id_sample_by_mutator = [no_dash.replace('-', '') for no_dash in extract_sampled_bug_id_list_for_mutators(std_ids_dir_path)]
    else:
        sys.exit('invalid argument')
    
    op_count = {}
    fix_count = {}
    search_space = 100
    for proj in projects:
        print('verifying ' + proj)
        mutants = {}
        mutants_log_path = join(mutants_root_dir, proj if mode == 'small' else proj + '-1f', 'mutants.log')

        extract_patch(buggy_csv_path)
        op_map = extract_op(mutants_log_path)
        verify(proj, patch_csv_path, bug_id_sample_by_mutator)
        
        if mode == 'd4j_add':
            mutants = {}
            extract_patch(buggy_add_csv_path)
            op_map = extract_op(mutants_log_path)
            verify(proj, patch_add_csv_path, sampled_ids=bug_id_sample_by_mutator)
            
    print(op_count)
    print(fix_count)
    print(sum(int(x) for x in op_count.values()))