import os,sys
sys.path.append(os.path.abspath(__file__ + '/../'))
from os.path import *
from defects4j import *
from major_report_parser import *
from utils import fix_proj_1f, rela_to_abs_path, get_config_dir
from config_loader import load_config

def show_patch(d4j_proj:Defects4J, mutant_id:str, patch_id:str, tool:str):
    proj = d4j_proj.project_id
    mutant_dir = join(mutant_root_dir, fix_proj_1f(proj + '-1f'), 'mutants', mutant_id)
    assert isdir(mutant_dir), mutant_dir
    buggy_file = mutant_dir
    while (not isfile(buggy_file)) or (not buggy_file.endswith('.java')):
        buggy_file = join(buggy_file, os.listdir(buggy_file)[0])
        rela_path = relpath(buggy_file, mutant_dir)
    assert isfile(buggy_file) and buggy_file.endswith('.java')
    if tool == 'sequencer':
        patch_dir = join(patch_root_dir, proj + mutant_id)
        if not isdir(patch_dir): patch_dir = patch_dir.replace('src_patches', 'src_patches_add')
        patched_file_path = join(patch_dir, patch_id, os.listdir(join(patch_dir, patch_id))[0])
    elif tool in ['reward_repair', 'recoder', 'simfix', 'selfapr', 'alpha_repair']:
        patch_dir = join(patch_root_dir, proj, mutant_id)
        if not isdir(patch_dir): patch_dir = patch_dir.replace('src_patches', 'src_patches_add')
        patched_file_path = join(patch_dir, patch_id + '.java')
    elif tool in ['tbar', 'cure', 'coconut', 'tufano', 'edits']:
        patch_dir = join(patch_root_dir, proj + '_' + mutant_id, 'patches-pool')
        if not isdir(patch_dir): patch_dir = patch_dir.replace('src_patches', 'src_patches_add')
        patched_file_path = subprocess.check_output('find {} -name "*.java"'.format(join(patch_dir, patch_id)), shell=True, universal_newlines=True).strip()
    else:
        print('Unknown tool: ' + tool)
    assert isfile(patched_file_path), patched_file_path
    os.system('diff -u -w ' + buggy_file + ' ' + patched_file_path)
    
if __name__ == '__main__':
    tool = sys.argv[1]
    proj = sys.argv[2]
    mutant_id = sys.argv[3]
    patch_id = sys.argv[4]
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    mutant_root_dir = rela_to_abs_path(common_config['mutant_root_dir'])
    repo_root_dir = rela_to_abs_path(common_config['validation_repo_root_dir'])
    patch_root_dir = rela_to_abs_path(join(common_config['patch_root_dir'], tool + '_patches'))
    bug_id = '13' if proj == 'mockito' else '25' if proj == 'collections' else '1'
    d4j_proj = Defects4J(proj, bug_id, 'fixed', join(repo_root_dir, proj))
    d4j_proj.clean()
    show_patch(d4j_proj, mutant_id, patch_id, tool)