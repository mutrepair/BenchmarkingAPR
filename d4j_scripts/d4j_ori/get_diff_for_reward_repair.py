import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../../'))
from defects4j import *
from d4j_ori.d4j_info import *
from utils import *
from d4j_ori.dump_buggy_oracle_patch import get_oracle_patch_path
from config_loader import load_config

def count_file_lines(file):
    return len(file_to_ids(file))

if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    reward_repair_config = load_config(join(get_config_dir(), 'reward_repair', 'd4j_ori.yaml'))
    repo_root_dir = rela_to_abs_path(common_config['d4j_buggy_repo_root_dir'])
    diff_root_dir = rela_to_abs_path(reward_repair_config['diff_dir'])
    oracle_patch_dir = rela_to_abs_path(common_config['d4j_oracle_patch_dir'])
    bug_ids = load_single_line_bug_ids()
    os.makedirs(diff_root_dir, exist_ok=True)
    for bug_id in bug_ids:
        proj_name, id = bug_id.split('_')
        d4j_proj = Defects4J(proj_name, id, 'buggy', join(repo_root_dir, bug_id))
        d4j_proj.checkout_if_not_exist_or_empty()
        buggy_file_path = d4j_proj.get_buggy_file_path()
        fixed_file_path, rela_path = get_oracle_patch_path(oracle_patch_dir, bug_id)
        line_no, patch_line = get_oracle_patch_line(bug_id)
        line_num = max(count_file_lines(buggy_file_path), count_file_lines(fixed_file_path))
        diff_path = mkdir_for_file(join(diff_root_dir, bug_id.replace('_', '') + '.diff'))
        cmd = 'diff --unified={} {} {} > {}'.format(line_num, buggy_file_path, fixed_file_path, diff_path)
        print(cmd)
        os.system(cmd)