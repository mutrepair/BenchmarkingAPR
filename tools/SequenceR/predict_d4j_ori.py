import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../../../d4j_scripts'))
from defects4j import *
from utils import *
from d4j_ori.d4j_info import load_single_line_bug_ids, get_oracle_patch_line
from config_loader import load_config

if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    sequencer_config = load_config(join(get_config_dir(), 'sequencer', 'd4j_ori.yaml'))
    beam_size = sequencer_config['beam_size']
    model_path = rela_to_abs_path(sequencer_config['model_path'])
    repo_root_dir = rela_to_abs_path(common_config['d4j_buggy_repo_root_dir'])
    bug_id_list = load_single_line_bug_ids()
    log_dir = rela_to_abs_path(sequencer_config['log_dir'])
    output_root_dir = rela_to_abs_path(sequencer_config['output_dir'])
    os.makedirs(log_dir, exist_ok=True)
    
    for bug_id in bug_id_list:
        proj_name, id = bug_id.split('_')
        d4j_proj = Defects4J(proj_name, id, 'buggy', join(repo_root_dir, bug_id))
        d4j_proj.checkout_if_not_exist_or_empty()
        buggy_file_path = d4j_proj.get_buggy_file_path()
        buggy_line_no, oracle_patch_line = get_oracle_patch_line(bug_id)
        log_path = join(log_dir, bug_id + '.log')
        
        os.system('src/sequencer-predict.sh --model={} --buggy_file={} --buggy_line={}\
            --beam_size={} --output={} > {}'.format(model_path, buggy_file_path, buggy_line_no, beam_size, join(output_root_dir, bug_id.replace('_', '')), log_path))
        os.system('cat ' + log_path)