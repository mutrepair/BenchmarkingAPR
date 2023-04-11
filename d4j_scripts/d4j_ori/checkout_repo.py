import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../../'))
from defects4j import *
from utils import *
from config_loader import *

if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    buggy_repo_root_dir = rela_to_abs_path(common_config['d4j_buggy_repo_root_dir'])
    fixed_repo_root_dir = rela_to_abs_path(common_config['d4j_fixed_repo_root_dir'])
    single_bug_ids = file_to_ids(abspath(dirname(__file__)) + '/../resources/single_line_bug_list.txt')
    for bug_id in single_bug_ids:
        proj_name, id = bug_id.split('_')
        d4j_proj = Defects4J(proj_name, id, 'buggy', join(buggy_repo_root_dir, bug_id))
        d4j_proj.checkout()
        
    for bug_id in single_bug_ids:
        proj_name, id = bug_id.split('_')
        d4j_proj = Defects4J(proj_name, id, 'fixed', join(fixed_repo_root_dir, bug_id + 'f'))
        d4j_proj.checkout()