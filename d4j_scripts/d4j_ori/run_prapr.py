import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../../'))
from utils import *
from defects4j import Defects4J, get_identifier_from_lower_name
from d4j_info import *
import subprocess
import shutil
from config_loader import load_config

def save_result(result_src_dir, result_tgt_dir):
    os.makedirs(result_tgt_dir, exist_ok=True)
    for file in os.listdir(result_src_dir):
        file_path = join(result_src_dir, file)
        if isdir(file_path):
            shutil.copytree(file_path, join(result_tgt_dir, file))
        else:
            shutil.copy(file_path, join(result_tgt_dir, file))
            
def get_accurate_statement_line(ori_file_path:str, buggy_line_no:int):
    with open(ori_file_path) as f:
        lines = f.readlines()
    for i in range(buggy_line_no - 2, -1, -1):
        line = lines[i].strip()
        if line.find('//') != -1:
            line = line[:line.find('//')].strip()
        if line.endswith('*/') and line.find('/*') != -1:
            line = line[:line.find('/*')].strip()
        if line == '' or line == '{' or line == '}' or line.endswith(';') \
            or line.endswith('{') or line.endswith('}') or line.endswith('else') \
                or line.endswith(':') or line.endswith('*/'):
                assert (buggy_line_no - i - 1 <= 15), (buggy_line_no - i - 1)
                return i + 2
    assert False, 'No accurate statement line found for ' + ori_file_path + ':' + str(buggy_line_no)        

if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    prapr_config = load_config(join(get_config_dir(), 'prapr', 'd4j_ori.yaml'))
    ref_repo_root_dir = rela_to_abs_path(common_config['ref_repo_dir'])
    repo_root_dir = rela_to_abs_path(prapr_config['repo_root_dir'])
    failing_tests_root_dir = rela_to_abs_path(prapr_config['failing_tests_root_dir'])
    mutate_susp_stmt = 'PERFECT'
    bug_id_list = load_single_line_bug_ids()
    result_dir = rela_to_abs_path(prapr_config['result_dir'])
    
    for bug_id in bug_id_list:
        print('running ' + bug_id)
        proj_name, id = bug_id.split('_')
        result_for_bug_dir = join(result_dir, proj_name, id)
        if isdir(result_for_bug_dir) and len(os.listdir(result_for_bug_dir)) > 0:
            print('Skip ' + bug_id)
            continue
        proj_dir = join(repo_root_dir, get_identifier_from_lower_name(proj_name), id)
        ori_pom_path = join(proj_dir, 'pom.xml')
        if proj_name == 'gson':
            ori_pom_path = join(proj_dir, 'gson', 'pom.xml')
        assert isfile(ori_pom_path), ori_pom_path
        d4j_proj = Defects4J(proj_name, id, 'buggy', proj_dir)
        d4j_proj_ref = Defects4J(proj_name, id, 'buggy', join(ref_repo_root_dir, get_identifier_from_lower_name(proj_name), id))
        d4j_proj_ref.checkout_if_not_exist_or_empty()
        faulty_class = d4j_proj_ref.get_buggy_class_name()
        faulty_statement_line = str(get_accurate_statement_line(d4j_proj_ref.get_buggy_file_path(), get_oracle_patch_line(bug_id)[0]))
        failing_tests = d4j_proj_ref.get_trigger_tests()
        d4j_proj.add_pom_with_perfect_fl_from_scratch(faulty_class, faulty_statement_line, failing_tests, \
            src_pom_path=ori_pom_path, tgt_pom_name='pom_for_prapr_perfect.xml')
        
        try:
            d4j_proj.run_prapr(pom_name='pom_for_prapr_perfect.xml', mutate_susp_stmt=mutate_susp_stmt)
        except subprocess.CalledProcessError as e:
            print(e.output.decode('utf-8'))
            print('prapr run error for bug: ' + bug_id)
            sys.exit(1)
        
        prapr_report_dir = join(proj_dir, 'target', 'prapr-reports')
        if proj_name == 'gson':
            prapr_report_dir = join(proj_dir, 'gson', 'target', 'prapr-reports')
        save_result(join(prapr_report_dir, os.listdir(prapr_report_dir)[0]), \
            result_for_bug_dir)