import sys, os
from os.path import *
sys.path.append(dirname(dirname(abspath(__file__))))
from plausiblePatches1700 import *
from utils import file_to_ids
from major_report_parser import load_mutants
from defects4j import all_projects

# def reformat_patch_id(patch_id):
#     return patch_id.replace('')

def get_simfix_plausible_patches(simfix_result_dir):
    simfix_plausible_patches = []
    for proj_1f in os.listdir(simfix_result_dir):
        proj = proj_1f.split('-')[0]
        proj_dir = join(simfix_result_dir, proj_1f)
        for mutant_id in os.listdir(proj_dir):
            res_dir = join(proj_dir, mutant_id)
            correct_dir = join(res_dir, 'correct')
            if isdir(correct_dir):
                for patch_id in os.listdir(correct_dir):
                    full_patch_id = proj + '-' + mutant_id + '-' + patch_id.split('.')[0]
                    simfix_plausible_patches.append(full_patch_id)
                    
    return simfix_plausible_patches

def get_prapr_plausible_patches(prapr_result_dir):
    prapr_plausible_patches = []
    for proj_1f in os.listdir(prapr_result_dir):
        proj = proj_1f.split('-')[0]
        proj_dir = join(prapr_result_dir, proj_1f)
        for mutant_id in os.listdir(proj_dir):
            res_dir = join(proj_dir, mutant_id)
            pool_dir = join(res_dir, 'pool')
            for patch_id in os.listdir(pool_dir):
                full_patch_id = proj + '-' + mutant_id + '-' + patch_id.split('.')[0].split('-')[-1]
                prapr_plausible_patches.append(full_patch_id)
                
    return prapr_plausible_patches

def get_op_map(mutants_dir, bug_id_list):
    mutants = {}
    op_map = {}
    for proj in all_projects:
        mutants[proj] = load_mutants(proj, mutants_dir)
        
    for bug_id in bug_id_list:
        proj, mutant_id = bug_id.split('_')
        op_map[bug_id] = mutants[proj][mutant_id]['operator']
        
    return op_map

if __name__ == '__main__':
    bug_ids = set(file_to_ids('../resources/sample_1700_mutants.txt'))
    simfixPlausiblePatches = get_simfix_plausible_patches('/home/jun/fastd/dlapr-mirror/apr_patches/simfix')
    praprPlausiblePatches = get_prapr_plausible_patches('/home/jun/research/dlapr/prapr_result')
    mutants_dir = '/home/jun/research/dlapr/all_mutants'
    op_map = get_op_map(mutants_dir, bug_ids)
    
    all_plausible_patches = []
    all_plausible_patches += recoderPlausiblePatches
    all_plausible_patches += rewardRepairPlausiblePatches
    all_plausible_patches += tufanoPlausiblePatches
    all_plausible_patches += selfaprPlausiblePatches
    all_plausible_patches += coconutPlausiblePatches
    all_plausible_patches += curePlausiblePatches
    all_plausible_patches += sequencerPlausiblePatches
    all_plausible_patches += tbarPlausiblePatches
    all_plausible_patches += editsPlausiblePatches
    all_plausible_patches += alphaRepairPlausiblePatches
    all_plausible_patches += simfixPlausiblePatches
    all_plausible_patches += praprPlausiblePatches
    
    all_plausible_patches = set(all_plausible_patches)
    # print('all plausible patches: ', len(all_plausible_patches))
    
    for full_patch_id in all_plausible_patches:
        proj, mutant_id, patch_id = full_patch_id.split('-')
        bug_id = proj + '_' + mutant_id
        if bug_id in bug_ids:
            bug_ids.remove(bug_id)
        
    print('bug ids without plausible patches: ', len(bug_ids))
    for bug_id in bug_ids:
        print(bug_id, op_map[bug_id])