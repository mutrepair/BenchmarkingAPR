import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../../'))
from utils import file_to_ids, find_mutant_file, rela_to_abs_path, get_config_dir
from defects4j import Defects4J
from config_loader import load_config

def get_oracle_patch_path(target_patch_dir:str, bug_id:str) -> tuple:
    proj, id = bug_id.split('_')
    patch_dir = join(target_patch_dir, proj, id, 'patch')
    assert isdir(patch_dir), 'Patch dir not found: {}'.format(patch_dir)
    return find_mutant_file(patch_dir)

if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    repo_root_dir = common_config['d4j_buggy_repo_root_dir']
    target_patch_dir = join(abspath(dirname(__file__)), '..', 'resources', 'd4j_ori_oracle_patches')
    target_buggy_dir = join(abspath(dirname(__file__)), '..', 'resources', 'd4j_ori_buggy_files')
    bug_ids = file_to_ids(join(abspath(dirname(__file__)), '..', 'resources', 'single_line_bug_list.txt')) # currently only support single line bugs
    for bug_id in bug_ids:
        proj_name, id = bug_id.split('_')
        d4j_proj = Defects4J(proj_name, id, 'buggy', join(repo_root_dir, bug_id))
        d4j_proj.checkout_if_not_exist_or_empty()
        d4j_proj.export_oracle_patch(target_patch_dir)
        d4j_proj.export_buggy_file(target_buggy_dir)