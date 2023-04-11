import os,sys
import subprocess
from os.path import *
import re
sys.path.append(os.path.abspath(__file__ + '/../../../d4j_scripts'))
from config_loader import load_config
from utils import get_config_dir, rela_to_abs_path

def find_file_in_dir(dir):
    file = dir
    while (isdir(file)):
        file = join(file, os.listdir(file)[0])
    return file

def file_to_ids(file):
    with open(file, 'r') as f:
        lines = f.readlines()
    ids = []
    for line in lines:
        ids.append(line.strip())
    return ids

def extract_line_no(mutants_log_file:str, sample_id:str):
    with open(mutants_log_file) as f:
        lines = f.readlines()
    for line in lines:
        line = line.strip()
        if line.startswith(sample_id + ':'):
            try:
                line_no = int(re.findall(r":[0-9]+:", line)[-1][1:-1])
                # print(line_no)
                assert line_no != 0
                return line_no
            except:
                print(mutants_log_file)
                print(line)
    assert False, "No match found"

if __name__ == '__main__':
    mode = sys.argv[1]
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    dataset_config = load_config(join(get_config_dir(), 'common', 'dataset.yaml'))
    sequencer_config = load_config(join(get_config_dir(), 'sequencer', 'mutants.yaml'))
    beam_size = sequencer_config['beam_size']
    all_mutants_dir = rela_to_abs_path(common_config['mutant_root_dir'])
    model_path = rela_to_abs_path(sequencer_config['model_path'])
    if mode == 'd4j':
        sample_id_dir = rela_to_abs_path(dataset_config['sample_1700_list_dir'])
        log_dir = rela_to_abs_path(sequencer_config['log_dir'])
        output_root_dir = rela_to_abs_path(sequencer_config['output_dir'])
    elif mode == 'd4j_add':
        sample_id_dir = rela_to_abs_path(dataset_config['sample_1700_add_list_dir'])
        log_dir = rela_to_abs_path(sequencer_config['log_add_dir'])
        output_root_dir = rela_to_abs_path(sequencer_config['output_add_dir'])
    else:
        raise ValueError('mode should be d4j or d4j_add', mode)
    os.makedirs(log_dir, exist_ok=True)
    
    for proj in os.listdir(all_mutants_dir):
        if not isdir(join(all_mutants_dir, proj)): continue
        sampled_id_file = join(sample_id_dir, proj, 'sampledMutIds.txt')
        if not isfile(sampled_id_file): continue
        sampled_ids = file_to_ids(sampled_id_file)
        mutants_log_file = join(all_mutants_dir, proj, 'mutants.log')
        mutants_dir = join(all_mutants_dir, proj, 'mutants')
        for id in sampled_ids:
            mutant_id = proj.split('-')[0] + id
            log_file = join(log_dir, mutant_id + '.log')
            line_no = extract_line_no(mutants_log_file, id)
            mutant_dir = join(mutants_dir, id)
            assert isdir(mutant_dir)
            buggy_file = find_file_in_dir(mutant_dir)
            print(buggy_file)
            print(line_no)
            assert isfile(buggy_file), buggy_file
            output_dir = join(output_root_dir, mutant_id)
            os.makedirs(output_dir, exist_ok=True)
            os.system('src/sequencer-predict.sh --model={} --buggy_file={} --buggy_line={}\
                --beam_size={} --output={} > {}'.format(model_path,\
                    buggy_file, line_no, beam_size, output_dir, log_file))
            os.system('cat ' + log_file)