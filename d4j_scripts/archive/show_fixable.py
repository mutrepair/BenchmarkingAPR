import sys, os
sys.path.append(os.path.abspath(__file__ + '/../'))
from verify_all import patch_tokens_identical, patch_tokens_identical_recoder, patch_tokens_identical_strict
from token_utils import *
from major_report_parser import *
from defects4j import get_fst_v_src_rela_dir
from utils import *

if __name__ == '__main__':
    grammar_tokens_file = './resources/chars.txt'
    grammar_tokens = load_tokens(grammar_tokens_file)
    
    patch_src_root_dir = '/home/jun/fastd/dlapr-mirror/validation/src_patches'
    prapr_patch_dir = '/home/jun/research/dlapr/prapr_result'
    sampled_id_dir = '/home/jun/research/dlapr/sample1700'
    if len(sys.argv) > 3:
        tools = sys.argv[3:]
    else: tools = ['coconut', 'cure', 'edits', 'recoder', 'reward_repair', 'selfapr', 'sequencer', 'tbar', 'tufano', 'simfix', 'prapr']
    mutant_root_dir = '/home/jun/research/dlapr/all_mutants'
    repo_root_dir = '/home/jun/fastd/dlapr-mirror/validation/validation_repo'
    
    proj = sys.argv[1]
    mutant_id = sys.argv[2]
    mutants_all = {}
    mutants_all[proj] = load_mutants(proj, mutant_root_dir)
    
    for tool in tools:
        if tool == 'prapr': # prapr is special and we rely on its report file
            tool_patch_dir = join(prapr_patch_dir, proj + '-1f', mutant_id)
            report_file = join(tool_patch_dir, 'all-report.log')
            with open(report_file, 'r') as f:
                lines = f.readlines()
            fst_line = lines[0]
            if int(fst_line.split(' ')[-1]) > 0:
                print("{} can be plausibly fixed by {}, the report file is {}".format(proj + '-' + mutant_id, tool, report_file))
            else:
                print('{} cannot be fixed by {}, the report file is {}'.format(proj + '-' + mutant_id, tool, report_file))
            continue
        mutant = mutants_all[proj][mutant_id]
        line_no = mutant['line_no']
        
        ori_file_path = join(repo_root_dir, proj, get_fst_v_src_rela_dir(proj), mutant['path'])
        correct_line = get_line(ori_file_path, line_no)
        correct_line_tokens = java_tokenize(correct_line, grammar_tokens)
        
        buggy_file_path = join(mutant_root_dir, proj + '-1f', 'mutants', mutant_id, mutant['path'])
        buggy_line = get_line(buggy_file_path, line_no)
        buggy_line_tokens = java_tokenize(buggy_line, grammar_tokens)
        
        tool_patch_dir = join(patch_src_root_dir, tool + '_patches')
        
        if tool in ['reward_repair', 'recoder', 'simfix', 'selfapr']:
            patch_dir = join(tool_patch_dir, proj, mutant_id)
        elif tool == 'sequencer':
            patch_dir = join(tool_patch_dir, proj + mutant_id)
        elif tool in ['tbar', 'edits', 'tufano', 'coconut', 'cure']:
            patch_dir = join(tool_patch_dir, proj + '_' + mutant_id, 'patches-pool')
        if not isdir(patch_dir):
            print('patch directory not found: ' + patch_dir)
            continue
        
        fixable = False
        for file in os.listdir(patch_dir):
            if tool in ['recoder', 'simfix']:
                # recoder and simfix patches require special handling because they are not single line patches
                patch_id = file.split('.')[0]
                patched_file_path = join(patch_dir, file)
                buggy_line, patched_line = get_buggy_and_patched_line(buggy_file_path, patched_file_path)
                buggy_line_tokens = java_tokenize(buggy_line, grammar_tokens)
            elif tool in ['tbar', 'tufano']:
                assert isdir(join(patch_dir, file))
                patch_id = file
                patched_file_path = join(patch_dir, file, mutant['path'])
                buggy_line, patched_line = get_buggy_and_patched_line(buggy_file_path, patched_file_path)
                buggy_line_tokens = java_tokenize(buggy_line, grammar_tokens)
            elif tool in ['reward_repair', 'selfapr']:
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
                
            patch_line_tokens = java_tokenize(patched_line, grammar_tokens)
            if patch_tokens_identical(correct_line_tokens, patch_line_tokens, tool):
                fixable = True
                break
        if fixable:
            print("{} can be fixed by {}, the syntactically identical patch is {}".format(proj + '-' + mutant_id, tool, join(patch_dir, file)))
        else:
            print("{} cannot be fixed by {}".format(proj + '-' + mutant_id, tool))