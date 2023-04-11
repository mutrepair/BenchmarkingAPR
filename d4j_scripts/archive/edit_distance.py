import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../'))
from Levenshtein import distance
import numpy as np
import csv
import sys
from scipy.stats import mannwhitneyu
import matplotlib.pyplot as plt
import collections
import re
from utils import *
from major_report_parser import *
from defects4j import all_projects, Defects4J, get_fst_v_src_rela_dir
from tokenizers.implementations import BertWordPieceTokenizer
from token_utils import *

def get_distance(str1, str2):
    if tokenizer == 'custom':
        result = get_distance_with_custom(str1, str2)
    return result
    
def get_distance_with_custom(str1, str2):
    tokens1 = java_tokenize(str1, grammar_tokens)
    tokens2 = java_tokenize(str2, grammar_tokens)
    return distance(tokens1, tokens2)

def get_distance_list(patch_dict):
    distance_list = []
    for bug_id in patch_dict.keys():
        for patch_id, patched_line, buggy_line, distance in patch_dict[bug_id]:
            distance_list.append(distance)
    return distance_list

def reject_outliers(data, m=3):
    filtered = []
    m_sigma = m * np.std(data)
    for d in data:
        if abs(d - np.mean(data)) < m_sigma:
            filtered.append(d)
    return filtered

if __name__ == '__main__':
    tokenizer = sys.argv[1]
    assert tokenizer == 'custom'
    grammar_tokens_file = './resources/chars.txt'
    grammar_tokens = load_tokens(grammar_tokens_file)
    output_dir = '/home/jun/research/dlapr/edit_distance_analysis_' + tokenizer 
    
    patch_src_root_dir = '/home/jun/fastd/dlapr-mirror/validation/src_patches'
    sampled_id_dir = '/home/jun/research/dlapr/sample1700'
    tools = ['coconut', 'cure', 'edits', 'recoder', 'reward_repair', 'sequencer', 'tbar', 'tufano', 'simfix', 'selfapr', 'alpha_repair']
    sampled_id_list = extract_sampled_bug_id_list(sampled_id_dir)
    mutant_root_dir = '/home/jun/research/dlapr/all_mutants'
    repo_root_dir = '/home/jun/fastd/dlapr-mirror/validation/validation_repo'
    mutants_all = {}
    src_rela_dir_map = {}
    patch_all_map = {}
    
    for proj in all_projects:
        mutants_all[proj] = load_mutants(proj, mutant_root_dir)
    
    for tool in tools:
        print('running tool: ', tool)
        output_txt = join(output_dir, tool + '_distance.txt')
        if not isfile(output_txt):
            patch_all_map[tool] = {}
            for sampled_id in sampled_id_list:
                proj, mutant_id = sampled_id.split('_')
                mutant = mutants_all[proj][mutant_id]
                line_no = mutant['line_no']
                ori_file_path = join(repo_root_dir, proj, get_fst_v_src_rela_dir(proj), mutant['path'])
                buggy_file_path = join(mutant_root_dir, proj + '-1f', 'mutants', mutant_id, mutant['path'])
                buggy_line = get_line(buggy_file_path, line_no)
                tool_patch_dir = join(patch_src_root_dir, tool + '_patches')
                
                if tool in ['reward_repair', 'recoder', 'simfix']:
                    patch_dir = join(tool_patch_dir, proj, mutant_id)
                elif tool == 'sequencer':
                    patch_dir = join(tool_patch_dir, proj + mutant_id)
                elif tool in ['tbar', 'edits', 'tufano', 'coconut', 'cure']:
                    patch_dir = join(tool_patch_dir, proj + '_' + mutant_id, 'patches-pool')
                if not isdir(patch_dir):
                    print('patch directory not found: ' + patch_dir)
                    continue
                
                for file in os.listdir(patch_dir):
                    if tool in ['recoder', 'simfix']:
                        # recoder and simfix patches require special handling because they are not single line patches
                        patch_id = file.split('.')[0]
                        patched_file_path = join(patch_dir, file)
                        buggy_line, patched_line = get_buggy_and_patched_line(buggy_file_path, patched_file_path)
                    elif tool in ['tbar', 'tufano']:
                        assert isdir(join(patch_dir, file))
                        patch_id = file
                        patched_file_path = join(patch_dir, file, mutant['path'])
                        buggy_line, patched_line = get_buggy_and_patched_line(buggy_file_path, patched_file_path)
                    elif tool in ['reward_repair', 'selfapr', 'alpha_repair']:
                        assert file.endswith('.java')
                        patch_id = file.split('.')[0]
                        patched_line = get_line(join(patch_dir, file), line_no)
                    elif tool == 'sequencer':
                        assert isdir(join(patch_dir, file))
                        patch_id = file
                        patched_line = get_line(join(patch_dir, file, mutant['path'].split('/')[-1]), line_no)
                    elif tool in ['edits', 'coconut', 'cure']:
                        assert isdir(join(patch_dir, file))
                        patch_id = file
                        patched_line = get_line(join(patch_dir, file, mutant['path']), line_no)
                    else:
                        Exception('illegal file: ' + file)
                    # print(tool, proj, mutant_id, patch_id)
                    patch_all_map[tool][sampled_id] = patch_all_map[tool].get(sampled_id, []) + \
                        [(patch_id, buggy_line, patched_line, get_distance(buggy_line, patched_line))]
		    
            distance_list = get_distance_list(patch_all_map[tool])
            save_list(output_txt, distance_list)
        else:
            distance_list = []
            with open(output_txt, 'r') as f:
                for line in f:
                    distance_list.append(int(line.strip()))
        
        print('statistics for ' + tool)
        print('average distance:', np.average(distance_list))
        print('median distance:', np.median(distance_list))
        
        c = collections.Counter(reject_outliers(distance_list))
        c = sorted(c.items())
        distance_set = [x[0] for x in c]
        freq = [x[1] for x in c]
        plt.bar(distance_set, freq)
        ax = plt.gca()
        ax.xaxis.set_major_locator(plt.MultipleLocator(10))
    plt.savefig(join('/home/jun/research/dlapr/edit_distance_analysis_' + tokenizer, 'distance.png'))
    plt.close()
