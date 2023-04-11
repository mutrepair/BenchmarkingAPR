import os,sys, csv
from os.path import *
sys.path.append(abspath(__file__ + '/../'))
from defects4j import all_projects
from utils import extract_sampled_bug_id_list_for_mutators

def verify(proj, patch_root_dir, sampled_ids:list=None):
    for patch_dir in os.listdir(join(patch_root_dir)):
        bug_id = patch_dir
        if not bug_id.startswith(proj): continue
        if sampled_ids is not None and bug_id not in sampled_ids: continue
        fix_method_path = join(fix_methods_root_dir, bug_id + '.txt')
        buggy_method_path = join(buggy_methods_root_dir, bug_id + '.txt')
        fixed = False
        correct_fix_method_str = file_to_raw_str(fix_method_path)
        buggy_method_str = file_to_raw_str(buggy_method_path)
        for patch in os.listdir(join(patch_root_dir, patch_dir)):
            patch_path = join(patch_root_dir, patch_dir, patch)
            patch_method_str = file_to_raw_str(patch_path)
            if patch_method_str == correct_fix_method_str:
                fixed = True
        if fixed:
            op = op_map[bug_id]
            op_count[op] = op_count.get(op, 0) + 1
            fix_count[proj] = fix_count.get(proj, 0) + 1

def file_to_raw_str(file):
    with open(file) as f:
        content = f.read()
    # to be confirmed
    return str_to_raw_str(content)

def str_to_raw_str(string:str):
    return string.strip().replace('\n','').replace('\t','').replace(' ', '').replace('(', '').replace(')', '')

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

if __name__ == '__main__':
    mode = sys.argv[1]
    mutants_root_dir = '/home/jun/APR_FL/dlapr/sample_1700_mutants'
    patches_root_dir = '/home/jun/APR_FL/dlapr/mtapr_recorder/patches_sample_1700'
    patches_add_root_dir = '/home/jun/APR_FL/dlapr/mtapr_recorder/patches_sample_1700_add'
    fix_methods_root_dir = '/home/jun/APR_FL/dlapr/mtapr_recorder/ids_all_info/fix_methods'
    buggy_methods_root_dir = '/home/jun/APR_FL/dlapr/mtapr_recorder/ids_all_info/buggy_methods'
    sample_by_mutator_ids_dir_path = '/home/jun/APR_FL/dlapr/sample_by_mutator_ids'
    
    redundant_patch_map = {}
    lazy_patch_map = {}
    
    if mode == 'd4j':
        bug_id_sample_by_mutator = None
    elif mode == 'd4j_add':
        bug_id_sample_by_mutator = [no_dash.replace('-', '') for no_dash in extract_sampled_bug_id_list_for_mutators(sample_by_mutator_ids_dir_path)]
    else:
        sys.exit('invalid argument')
        
    op_count = {}
    fix_count = {}
    
    for proj in all_projects:
        print('verifying ' + proj)
        mutants_log_path = join(mutants_root_dir, proj + '-1f', 'mutants.log')
        op_map = extract_op(mutants_log_path)

        verify(proj, patches_root_dir, bug_id_sample_by_mutator)
        
        if mode == 'd4j_add':
            verify(proj, patches_add_root_dir, bug_id_sample_by_mutator)
            
    print(op_count)
    print(fix_count)