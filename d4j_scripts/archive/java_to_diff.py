import os,sys
from os.path import *
from utils import *
from defects4j import *

def parse_sequencer_patch(patch_root_dir, sampled_id_root_dir, repo_root_dir, all_mutants_dir, diff_output_dir, projects, patch_num):
    tool_name = 'sequencer'
    for proj in projects:
        repo_dir = join(repo_root_dir, proj + '-1f')
        bug_id = '13' if proj == 'mockito' else '25' if proj == 'collections' else '1'
        d4j_proj = Defects4J(proj, bug_id, 'fixed', repo_dir)
        d4j_proj.checkout_if_not_exist_or_empty()
        src_rela_path = d4j_proj.get_rela_src_path()
        sample_id_file = join(sampled_id_root_dir, proj + '-1f', 'sampledMutIds.txt')
        ids = file_to_ids(sample_id_file)
        for mutant_id in ids:
            patch_dir = join(patch_root_dir, proj + mutant_id)
            mutant_dir = join(all_mutants_dir, proj + '-1f', 'mutants', mutant_id)
            assert isdir(patch_dir) and isdir(mutant_dir)
            file_path, rela_file_path = find_mutant_file(mutant_dir)
            ori_file = join(repo_dir, src_rela_path, rela_file_path)
            for i in range(len(os.listdir(patch_dir))):
                patched_file = join(patch_dir, str(i + 1), file_path.split('/')[-1])
                if isfile(patched_file) and file_empty(patched_file):continue
                assert isfile(patched_file), patched_file
                diff_output_file = join(diff_output_dir, '-'.join([tool_name, proj, mutant_id, str(i + 1) + '.patch']))
                mkdir_for_file(diff_output_file)
                
                get_diff(ori_file, patched_file, diff_output_file)
                
if __name__ == '__main__':
    sequencer_patch_root_dir = '/home/jun/APR_FL/dlapr/sequencer/mtapr_output/sample'
    sampled_id_root_dir = '/home/jun/APR_FL/dlapr/sample_1012/d4jProjNewSample'
    repo_root_dir = '/home/jun/APR_FL/dlapr/repo_for_diff'
    all_mutants_dir = '/home/jun/APR_FL/dlapr/all_mutants'
    diff_output_dir = '/home/jun/APR_FL/dlapr/diff_to_ori_file'
    projects=['chart', 'cli', 'codec', 'compress', 'csv', 'gson','jacksoncore',\
    'jacksondatabind', 'jacksonxml', 'jsoup', 'jxpath', 'lang', 'time']
    patch_num = 100
    parse_sequencer_patch(sequencer_patch_root_dir, sampled_id_root_dir, repo_root_dir, all_mutants_dir, diff_output_dir, projects, patch_num)