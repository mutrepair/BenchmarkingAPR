import os
from os.path import *
from utils import file_to_ids, rela_to_abs_path, get_config_dir, get_script_dir
from config_loader import load_config

def file_to_content(file):
    with open(file, 'r') as f:
        return f.read()

def add_redundant_patch(bug_id:str, patch_content:str):
    if bug_id not in redundant_patch_map: 
        redundant_patch_map[bug_id] = dict() # key is patch antlr output, value is count
    else:
        redundant_patch_map[bug_id][patch_content] = redundant_patch_map[bug_id].get(patch_content, 0) + 1 
        
def add_lazy_patch(bug_id, patch_content, buggy_content):
    if patch_content == buggy_content:
        lazy_patch_map[bug_id] = lazy_patch_map.get(bug_id, 0) + 1

def print_stats_for_map(patch_map, name):
    print(name + ' stats:')
    count = 0
    bug_num = 0
    if name == 'redundant':
        for bug_id in patch_map:
            redundant = False
            for patch_method_str in patch_map[bug_id]:
                redundant_num = patch_map[bug_id][patch_method_str] - 1
                count += redundant_num
                if redundant_num > 0:
                    redundant = True
            if redundant:
                bug_num += 1
    if name == 'lazy':
        for bug_id in patch_map:
            lazy_num = patch_map[bug_id]
            count += lazy_num
            if lazy_num > 0:
                bug_num += 1
    print('total ' + name + ' patches: ' + str(count))
    print('number of bugs with ' + name + ' patches: ' + str(bug_num))
    
def get_patch_token_file_list_dict(patch_antlr_token_dir):
    patch_file_dict_for_bug = dict()
    for f in os.listdir(patch_antlr_token_dir):
        tool, proj, id, patch_id = f.split('-')
        tool_bug_id = '-'.join([tool, proj, id])
        patch_file_dict_for_bug[tool_bug_id] = patch_file_dict_for_bug.get(tool_bug_id, []) + [join(patch_antlr_token_dir, f)]
        
    return patch_file_dict_for_bug
    
if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    tools = ['coconut', 'cure', 'edits', 'recoder', 'reward_repair', 'selfapr', 'sequencer', 'tbar', 'tufano', 'simfix', 'alpha_repair']
    bug_id_list = file_to_ids(join(get_script_dir(), 'resources/sample_1700_mutants.txt'))
    patch_antlr_token_dir = rela_to_abs_path(join(common_config['patch_eq_root_dir'], 'mutant_patch_antlr_output'))
    buggy_antlr_token_dir = rela_to_abs_path(join(common_config['patch_eq_root_dir'], 'mutant_file_antlr_output'))
    
    patch_file_dict_for_bug = get_patch_token_file_list_dict(patch_antlr_token_dir)
    for tool in tools:
        print('running tool: ' + tool)
        redundant_patch_map = dict()
        lazy_patch_map = dict()
        
        for bug_id in bug_id_list:
            proj, id = bug_id.split('_')
            buggy_antlr_token_file_path = join(buggy_antlr_token_dir, proj + '-' + id + '.txt')
            
            patch_file_list = patch_file_dict_for_bug.get('-'.join([tool, proj, id]), [])
            
            for patch_token_file_path in patch_file_list:
                add_redundant_patch(bug_id, file_to_content(patch_token_file_path))
                add_lazy_patch(bug_id, file_to_content(patch_token_file_path), file_to_content(buggy_antlr_token_file_path))
            
        print_stats_for_map(redundant_patch_map, 'redundant')
        print_stats_for_map(lazy_patch_map, 'lazy')