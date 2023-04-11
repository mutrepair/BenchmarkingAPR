import sys
import os
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../'))
sys.path.append(os.path.abspath(__file__ + '/../../'))
from syn_eq_result_1700 import eqList as eq_list
from utils import file_to_ids
from major_report_parser import load_mutants
from defects4j import all_projects

def get_op_map(mutants_dir, bug_id_list):
    mutants = {}
    op_map = {}
    for proj in all_projects:
        mutants[proj] = load_mutants(proj, mutants_dir)
        
    for bug_id in bug_id_list:
        proj, mutant_id = bug_id.split('_')
        op_map[bug_id] = mutants[proj][mutant_id]['operator']
        
    return op_map

def reformat_patch_id(patch_id):
    return patch_id.replace('alphaRepair', 'alpha_repair').replace('rewardRepair', 'reward_repair')

def get_all_patch_ids(antlr_output_dir, prapr_output_dir):
    all_patches = []
    for file in os.listdir(antlr_output_dir):
        patch_full_id = file.split('.')[0]
        tool, proj, mutant_id, patch_id = patch_full_id.split('-')
        bug_id = proj + '_' + mutant_id
        all_patches.append(patch_full_id)
        
    prapr_patches = []        
    for proj_1f in os.listdir(prapr_output_dir):
        proj_dir = join(prapr_output_dir, proj_1f)
        for mutant_id in os.listdir(proj_dir):
            mutant_dir = join(proj_dir, mutant_id)
            pool_dir = join(mutant_dir, 'pool')
            for patch_file in os.listdir(pool_dir):
                patch_id = patch_file.split('.')[0].split('-')[-1]
                prapr_patches.append('-'.join(['prapr', proj_1f.split('-')[0], mutant_id, patch_id]))
    
    all_patches = all_patches + prapr_patches
    return all_patches

if __name__ == '__main__':
    bug_id_list = file_to_ids('../resources/sample_1700_mutants.txt')
    antlr_output_dir = '/home/jun/fastd/dlapr-mirror/validation/mutant_patch_antlr_output'
    prapr_output_dir = '/home/jun/research/dlapr/prapr_result'
    mutants_dir = '/home/jun/research/dlapr/all_mutants'
    patch_eq_list = [reformat_patch_id(patch_id) for patch_id in eq_list]
    op_map = get_op_map(mutants_dir, bug_id_list)

    can_fix_dict = {} # {bug_id: [tools]}
    for patch_full_id in patch_eq_list:
        tool, proj, mutant_id, patch_id = patch_full_id.split('-')
        bug_id = proj + '_' + mutant_id
        can_fix_dict[bug_id] = can_fix_dict.get(bug_id, []) + [tool]
        
    op_dist_map = {}
    for bug_id in bug_id_list:
        can_fix_tool_list = can_fix_dict.get(bug_id, [])
        if len(can_fix_tool_list) == 0:
            # print('bug id: ' + bug_id + ', operator: ' + op_map[bug_id])
            # op_set.add(op_map[bug_id])
            op_dist_map[op_map[bug_id]] = op_dist_map.get(op_map[bug_id], 0) + 1
            
    print(op_dist_map)