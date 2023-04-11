import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../../'))
from utils import *
from defects4j import Defects4J
from d4j_info import *
import subprocess
from config_loader import load_config

# do remember to record info of failing tests and rela dir

def dump_failing_test_info(failing_tests:list, output_file:str):
    mkdir_for_file(output_file)
    with open(output_file, 'w') as f:
        for test in failing_tests:
            f.write(test + '\n')
            
def dump_rela_dir_info(d4j_proj:Defects4J, output_file:str):
    mkdir_for_file(output_file)
    src_rela_path = d4j_proj.get_rela_src_path()
    bin_rela_path = d4j_proj.get_rela_bin_path()
    src_tests_rela_path = d4j_proj.get_rela_src_tests_path()
    bin_tests_rela_path = d4j_proj.get_rela_bin_tests_path()
    with open(output_file, 'w') as f:
        f.write('/' + src_rela_path + '\n')
        f.write('/' + bin_rela_path + '\n')
        f.write('/' + src_tests_rela_path + '\n')
        f.write('/' + bin_tests_rela_path)

def prepare_loc_file(class_name, line_no, loc_file_path):
    par_dir_path = dirname(loc_file_path)
    if not isdir(par_dir_path):
        os.makedirs(par_dir_path)
    # assert isfile(loc_file_path), loc_file_path
    print(loc_file_path)
    loc = class_name + '#' + str(line_no) + ',1.0,(-1,-1)'
    print(loc)
    with open(loc_file_path, 'w') as f:
        f.write(loc)
        
def run_simfix(proj_name, id, proj_dir):
    d4j_proj = Defects4J(proj_name, id, 'buggy', proj_dir)
    d4j_proj.checkout_if_not_exist_or_empty()
    d4j_proj.clean()
    buggy_class_name = d4j_proj.get_buggy_class_name()
    buggy_line_no = get_oracle_patch_line('_'.join([proj_name, id]))[0]
    log_path = join(log_root_dir, proj_name, id + '.log')
    if not isdir(dirname(log_path)):
        os.makedirs(dirname(log_path))
    class_path = ":".join([join(simfix_home, "bin")] + [join(simfix_home, "lib", dir) for dir in os.listdir(join(simfix_home, "lib"))])
    
    dump_failing_test_info(d4j_proj.get_trigger_tests(), join(failing_tests_root_dir, proj_name, id + '.txt'))
    dump_rela_dir_info(d4j_proj, join(rela_info_root_dir, proj_name, id + '.txt'))
    cmd = ["java", "-classpath", class_path, "cofix.main.Main", "--proj_home=" + repo_root_dir, "--proj_name=" + \
        proj_name, "--bug_id=" + id, "--oracle_faulty_class=" + buggy_class_name, \
        "--oracle_faulty_line=" + str(buggy_line_no)]
    
    print(' '.join(cmd))
    os.chdir(simfix_home)
    with open(log_path, 'w') as f:
        subprocess.call(cmd, stdout=f, stderr=f)
    
    
if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    simfix_config = load_config(join(get_config_dir(), 'simfix', 'd4j_ori.yaml'))
    simfix_home = simfix_config['simfix_home']
    repo_root_dir = simfix_config['repo_root_dir']
    loc_root_dir = join(simfix_home, 'd4j-info/location/ochiai')
    rela_info_root_dir = join(simfix_home, 'd4j-info/src_path')
    log_root_dir = simfix_config['log_dir']
    failing_tests_root_dir = simfix_config['failing_tests_dir']
    bug_id_list = load_single_line_bug_ids()
    result_root_dir = simfix_config['result_dir']
    
    for bug_id in bug_id_list:
        proj_name, id = bug_id.split('_')
        result_for_bug_dir = join(result_root_dir, proj_name, id)
        if isdir(result_for_bug_dir) and len(os.listdir(result_for_bug_dir)) > 0:
            print('skip', bug_id)
            continue
        else:
            print('Running SimFix for bug', bug_id)
            run_simfix(proj_name, id, join(repo_root_dir, proj_name, proj_name + '_' + id + '_buggy'))