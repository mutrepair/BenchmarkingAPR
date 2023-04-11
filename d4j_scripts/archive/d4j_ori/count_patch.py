import os,sys
sys.path.append(os.path.abspath(__file__ + '/../'))
sys.path.append(os.path.abspath(__file__ + '/../../'))
from d4j_info import *
from os.path import *
from defects4j import *
from utils import *

# tag for different types of patches
PLAUSIBLE = 'correct'
TEST_FAILURE = 'test_failure'
COMPILATION_FAILURE = 'compilation_failure'

def print_stats(count_dict:dict):
    num_total_patches = 0
    num_compilable_patches = 0
    num_plausible_patches = 0
    mutants_have_plausible_patches = 0
    mutants_have_compilable_patches = 0
    mutants_have_no_patch = 0
    for bug_id in count_dict:
        has_patch = False
        for tag in count_dict[bug_id]:
            num_total_patches += count_dict[bug_id][tag]
            if count_dict[bug_id][tag] > 0:
                has_patch = True
                
            if tag == PLAUSIBLE:
                num_plausible_patches += count_dict[bug_id][tag]
                num_compilable_patches += count_dict[bug_id][tag]
                if count_dict[bug_id][tag] > 0:
                    mutants_have_plausible_patches += 1
            elif tag == COMPILATION_FAILURE:
                pass
            elif tag == TEST_FAILURE:
                num_compilable_patches += count_dict[bug_id][tag]
        if not has_patch:
            mutants_have_no_patch += 1
        else:
            mutants_have_compilable_patches += 1
    
    print('Total number of patches: ' + str(num_total_patches))
    print('Total number of compilable patches: ' + str(num_compilable_patches))
    print('Total number of plausible patches: ' + str(num_plausible_patches))
    print('Total number of mutants that have compilable patches: ' + str(mutants_have_compilable_patches))
    print('Total number of mutants that have plausible patches: ' + str(mutants_have_plausible_patches))
    print('Total number of mutants that have no patch: ' + str(mutants_have_no_patch))
    
def count_prapr(prapr_result_dir:str, sampled_bug_ids:list):
    assert isdir(prapr_result_dir)
    count_dict = {}
    for bug_id in sampled_bug_ids:
        proj, id = bug_id.split('_')
        if version is not None:
            if not proj in projs:
                continue
        result_dir = join(prapr_result_dir, proj, id)
        count_dict[bug_id] = {}
        all_report_log = join(result_dir, 'all-report.log')
        assert isfile(all_report_log) 
        with open(all_report_log, 'r') as f:
            lines = f.readlines()
        count_dict[bug_id][PLAUSIBLE] = int(lines[0].split(' ')[-1])
        count_dict[bug_id][TEST_FAILURE] = int(lines[1].split(' ')[-1]) - int(lines[0].split(' ')[-1])
        count_dict[bug_id][COMPILATION_FAILURE] = 0
        
    print_stats(count_dict)

def count_simfix(simfix_result_dir:str, sampled_bug_ids:list):
    assert isdir(simfix_result_dir)
    count_dict = {}
    for bug_id in sampled_bug_ids:
        proj, id = bug_id.split('_')
        if version is not None:
            if not proj in projs:
                continue
        result_dir = join(simfix_result_dir, proj, id)
        count_dict[bug_id] = {}
        if not isdir(result_dir):
            count_dict[bug_id][PLAUSIBLE] = 0
            count_dict[bug_id][TEST_FAILURE] = 0
            count_dict[bug_id][COMPILATION_FAILURE] = 0
        else:
            for tag in [PLAUSIBLE, TEST_FAILURE, COMPILATION_FAILURE]:
                if isdir(join(result_dir, tag)):
                    count_dict[bug_id][tag] = len(os.listdir(join(result_dir, tag)))
                else:
                    count_dict[bug_id][tag] = 0
                    
    print_stats(count_dict)    

if __name__ == '__main__':
    # sampled_id_dir = '/home/jun/research/dlapr/sample1700'
    # sampled_bug_ids = extract_sampled_bug_id_list(sampled_id_dir)
    # tool = sys.argv[1]
    # simfix_result_dir = '/home/jun/research/dlapr/apr_patches/simfix'
    # prapr_result_dir = '/home/jun/research/dlapr/prapr_result'
    # if tool == 'simfix':
    #     count_simfix(simfix_result_dir, sampled_bug_ids)
    # elif tool == 'prapr':
    #     count_prapr(prapr_result_dir, sampled_bug_ids)
    
    d4j_bug_ids = load_single_line_bug_ids()
    tool = sys.argv[1]
    result_dir = sys.argv[2]
    version = None if len(sys.argv) < 4 else sys.argv[3]
    if version is not None:
        if version == '1.2':
            projs = v1_2_projects
        elif version == '2.0':
            projs = v2_0_add_projects
        else: 
            raise ValueError('Invalid version')
    if tool == 'simfix':
        count_simfix(result_dir, d4j_bug_ids)
    elif tool == 'prapr':
        count_prapr(result_dir, d4j_bug_ids)