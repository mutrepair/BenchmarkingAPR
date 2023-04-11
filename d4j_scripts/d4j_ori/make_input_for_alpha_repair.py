import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../../'))
from utils import *
from defects4j import Defects4J
from major_report_parser import *
from shutil import copyfile
from d4j_ori.d4j_info import load_single_line_bug_ids
from d4j_ori.dump_buggy_oracle_patch import get_oracle_patch_path
from config_loader import load_config

if __name__ == '__main__':
    alpha_repair_config = load_config(join(get_config_dir(), 'alpha_repair', 'd4j_ori.yaml'))
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    bug_ids = load_single_line_bug_ids()
    repo_root_dir = join(get_root_dir(), common_config['d4j_buggy_repo_root_dir'])
    alpha_repair_intput_dir = join(get_root_dir(), alpha_repair_config['alpha_repair_input_dir_d4j_ori'])
    oracle_patch_dir = join(get_script_dir(), 'resources', 'd4j_ori_oracle_patches')
    for bug_id in bug_ids:
        proj_name, id = bug_id.split('_')
        d4j_proj = Defects4J(proj_name, id, 'buggy', join(repo_root_dir, bug_id))
        d4j_proj.checkout_if_not_exist_or_empty()
        buggy_file_path = d4j_proj.get_buggy_file_path()
        fixed_file_path, _ = get_oracle_patch_path(oracle_patch_dir, bug_id)
        assert isfile(buggy_file_path) and isfile(fixed_file_path)
        
        target_dir = join(alpha_repair_intput_dir, bug_id)
        target_buggy_dir = join(target_dir, 'buggy')
        target_fixed_dir = join(target_dir, 'fixed')
        target_diff_dir = join(target_dir, 'diff_buggy_fixed')
        for subdir in [target_buggy_dir, target_fixed_dir, target_diff_dir]:
            os.makedirs(subdir, exist_ok=True)
        copyfile(buggy_file_path, join(target_buggy_dir, basename(buggy_file_path)))
        copyfile(fixed_file_path, join(target_fixed_dir, basename(fixed_file_path)))
        get_diff(buggy_file_path, fixed_file_path, join(target_diff_dir, 'src.patch'), ignore_space=False)