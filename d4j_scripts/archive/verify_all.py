import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../'))
from Levenshtein import distance
import sys
from utils import *
from major_report_parser import *
from defects4j import all_projects, Defects4J, get_fst_v_src_rela_dir
from token_utils import *

@DeprecationWarning
def patch_tokens_identical(patch_tokens, buggy_tokens, tool=None):
    if tool != 'recoder':
        return patch_tokens_identical_strict(patch_tokens, buggy_tokens)
    else:
        return patch_tokens_identical_recoder(patch_tokens, buggy_tokens)

@DeprecationWarning
def patch_tokens_identical_strict(patch_tokens, buggy_tokens):
    if len(patch_tokens) != len(buggy_tokens):
        return False
    for i in range(len(patch_tokens)):
        if patch_tokens[i] != buggy_tokens[i]:
            return False
    return True

@DeprecationWarning
def patch_tokens_identical_recoder(patch_tokens, buggy_tokens):
    if patch_tokens_identical_strict(patch_tokens, buggy_tokens):
        return True
    else:
        if len(patch_tokens) == len(buggy_tokens) + 2:
            k = 0
            for i in range(len(buggy_tokens)):
                j = i + k
                if patch_tokens[j] != buggy_tokens[i]:
                    if k == 0 and patch_tokens[j] == "(":
                        k = 1
                    elif k == 1 and patch_tokens[j] == ")":
                        k = 2
                    else:
                        return False
            return True
        else:
            return False

@DeprecationWarning
def add_redundant_patch(bug_id:str, patch_content:str):
    if bug_id not in redundant_patch_map: 
        redundant_patch_map[bug_id] = dict() # key is patch raw string, value is count
    else:
        redundant_patch_map[bug_id][patch_content] = redundant_patch_map[bug_id].get(patch_content, 0) + 1 
        
@DeprecationWarning
def add_lazy_patch(bug_id, patch_content, buggy_content):
    if patch_content == buggy_content:
        lazy_patch_map[bug_id] = lazy_patch_map.get(bug_id, 0) + 1

def print_stats_for_map(patch_map, name):
    print(name + ' stats:')
    count = 0
    if name == 'redundant':
        for bug_id in patch_map:
            for patch_method_str in patch_map[bug_id]:
                count += (patch_map[bug_id][patch_method_str] - 1)
    if name == 'lazy':
        for bug_id in patch_map:
            count += patch_map[bug_id]
    print('total ' + name + ' patches: ' + str(count))
    
if __name__ == '__main__':
    mode = sys.argv[1]
    
    grammar_tokens_file = './resources/chars.txt'
    grammar_tokens = load_tokens(grammar_tokens_file)
    
    patch_src_root_dir = '/home/jun/fastd/dlapr-mirror/validation/src_patches'
    sampled_id_dir = '/home/jun/research/dlapr/sample1700'
    sample_by_mutator_id_dir = '/home/jun/research/dlapr/sample_by_mutator_ids'
    tools = ['coconut', 'cure', 'edits', 'recoder', 'reward_repair', 'selfapr', 'sequencer', 'tbar', 'tufano', 'simfix', 'alpha_repair']
    if mode == 'd4j':
        sampled_id_list = extract_sampled_bug_id_list(sampled_id_dir)
    elif mode == 'd4j_add':
        sampled_id_list = extract_sampled_bug_id_list_for_mutators(sample_by_mutator_id_dir)
        sampled_id_list = [x.replace('-', '_') for x in sampled_id_list]
    mutant_root_dir = '/home/jun/research/dlapr/all_mutants'
    repo_root_dir = '/home/jun/fastd/dlapr-mirror/validation/validation_repo'
    mutants_all = {}
    
    for proj in all_projects:
        mutants_all[proj] = load_mutants(proj, mutant_root_dir)
    
    for tool in tools:
        print('running tool: ', tool)
        redundant_patch_map = dict() # key is bug_id, value is dict, key is patch_method_str, value is count
        lazy_patch_map = dict() # key is bug_id, value is count
        fix_count = {}
        op_count = {}
        for sampled_id in sampled_id_list:
            proj, mutant_id = sampled_id.split('_')
            mutant = mutants_all[proj][mutant_id]
            line_no = mutant['line_no']
            op = mutant['operator']
            
            ori_file_path = join(repo_root_dir, proj, get_fst_v_src_rela_dir(proj), mutant['path'])
            ori_raw_cleaned_content = file_to_clean_raw_str(ori_file_path)
            
            buggy_file_path = join(mutant_root_dir, proj + '-1f', 'mutants', mutant_id, mutant['path'])
            if not isfile(buggy_file_path):
                buggy_file_path = join(mutant_root_dir, proj + '-1f', 'mutants_add', mutant_id, mutant['path'])
            
            buggy_raw_cleaned_content = file_to_clean_raw_str(buggy_file_path)
            
            tool_patch_dir = join(patch_src_root_dir, tool + '_patches')
            
            if tool in ['reward_repair', 'recoder', 'simfix', 'selfapr', 'alpha_repair']:
                patch_dir = join(tool_patch_dir, proj, mutant_id)
            elif tool == 'sequencer':
                patch_dir = join(tool_patch_dir, proj + mutant_id)
            elif tool in ['tbar', 'edits', 'tufano', 'coconut', 'cure']:
                patch_dir = join(tool_patch_dir, proj + '_' + mutant_id, 'patches-pool')
            if not isdir(patch_dir):
                print('patch directory not found: ' + patch_dir)
                continue
            
            fixed = False
            
            for file in os.listdir(patch_dir):
                if tool in ['recoder', 'simfix', 'alpha_repair']:
                    patch_id = file.split('.')[0]
                    patched_file_path = join(patch_dir, file)
                elif tool in ['tbar', 'tufano']:
                    assert isdir(join(patch_dir, file))
                    patch_id = file
                    patched_file_path = join(patch_dir, file, mutant['path'])
                elif tool in ['reward_repair', 'selfapr']:
                    assert file.endswith('.java')
                    patch_id = file.split('.')[0]
                    patched_file_path = join(patch_dir, file)
                elif tool == 'sequencer':
                    assert isdir(join(patch_dir, file))
                    patch_id = file
                    patched_file_path = join(patch_dir, file, mutant['path'].split('/')[-1]) 
                elif tool in ['edits', 'coconut', 'cure']:
                    assert isdir(join(patch_dir, file))
                    patch_id = file
                    patched_file_path = join(patch_dir, file, mutant['path'])
                else:
                    Exception('illegal file: ' + file)
                
                assert isfile(patched_file_path)
                patch_raw_cleaned_content = file_to_clean_raw_str(patched_file_path)
                
                if patch_raw_cleaned_content == ori_raw_cleaned_content:
                    fixed = True
                    # print('-'.join([tool, proj, mutant_id, patch_id]))
                
                add_redundant_patch(sampled_id, patch_raw_cleaned_content)
                add_lazy_patch(sampled_id, patch_raw_cleaned_content, buggy_raw_cleaned_content)
                
            if fixed:
                print('-'.join([tool, proj, mutant_id]))
                fix_count[proj] = fix_count.get(proj, 0) + 1
                op_count[op] = op_count.get(op, 0) + 1
        print('fix count: ', fix_count)
        print('op count: ', op_count)
                    
        print_stats_for_map(redundant_patch_map, 'redundant')
        print_stats_for_map(lazy_patch_map, 'lazy')