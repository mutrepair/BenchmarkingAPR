import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import all_projects, get_fst_v_src_rela_dir
from utils import extract_sampled_bug_id_list_for_mutators, str_to_raw_str, get_line
from major_report_parser import *

def get_correct_patch_path(bug_id:str, proj_repo_dir:str):
    for proj in all_projects:
        if not bug_id.startswith(proj): continue
        mutant_id = bug_id.replace(proj, '')
        mutant = mutants[mutant_id]
        correct_file_path = join(proj_repo_dir, proj + '-1f', get_fst_v_src_rela_dir(proj), mutant['path'])
        assert isfile(correct_file_path), correct_file_path
        return correct_file_path

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
    for bug_id in os.listdir(patch_root_dir):
        if not bug_id.startswith(proj): continue
        if sampled_ids is not None and bug_id not in sampled_ids: continue
        patch_dir = join(patch_root_dir, bug_id)
        line_no = mutants[bug_id.replace(proj, '')]['line_no']
        correct_patch_path = get_correct_patch_path(bug_id, proj_repo_dir)
        correct_patch_str = str_to_raw_str(get_line(correct_patch_path, line_no))
        fixed = False
        for patch_id in os.listdir(patch_dir):
            tmp_listdir = os.listdir(join(patch_dir, patch_id))
            tmp_listdir.remove('diff')
            patch_path = join(patch_dir, patch_id, tmp_listdir[0])
            patch_str = str_to_raw_str(get_line(patch_path, line_no))
            if correct_patch_str == patch_str:
                fixed = True
                # print(bug_id, patch_id)
        if fixed:
            op = op_map[bug_id]
            op_count[op] = op_count.get(op, 0) + 1
            fix_count[proj] = fix_count.get(proj, 0) + 1

if __name__ == '__main__':
    d4j_projs = all_projects
    mode = sys.argv[1]

    mutants_root_dir = '/home/jun/research/dlapr/all_mutants'
    sample_by_mutator_ids_dir_path = '/home/jun/research/dlapr/sample_by_mutator_ids'
    patch_root_dir = '/home/jun/research/dlapr/sequencer/mtapr_output/sample'
    patch_add_root_dir = '/home/jun/research/dlapr/sequencer/mtapr_output/sample_add'
    proj_repo_dir = '/home/jun/fastd/dlapr-mirror/validation/validation_repo_ori'
    
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
        mutants = load_mutants(proj, mutants_root_dir)
        
        verify(proj, patch_root_dir, bug_id_sample_by_mutator)
        
        if mode == 'd4j_add':
            verify(proj, patch_add_root_dir, bug_id_sample_by_mutator)
                
    print(op_count)
    print(fix_count)