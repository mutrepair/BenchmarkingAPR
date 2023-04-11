import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import *
from utils import *
from config_loader import load_config

def record_failing_tests_for_mutants(d4j_proj:Defects4J, mutants_dir:str, mutant_ids:list, output_root_dir:str,\
    timeout_dir:str, compile_err_dir:str, no_fail_dir:str, specified_mutant_id:str=None):
    for mutant_id in mutant_ids:
        if specified_mutant_id is not None and mutant_id != specified_mutant_id: continue
        failing_tests_file = join(output_root_dir, fix_proj_1f(d4j_proj.project_id + '-1f'), mutant_id + '.txt')
        if isfile(failing_tests_file) and os.path.getsize(failing_tests_file) > 0: 
            print('failing tests result existis for mutant ' + mutant_id)
            continue
        mutant_dir = join(mutants_dir, 'mutants', mutant_id)
        if not isdir(mutant_dir): mutant_dir = join(mutants_dir, 'mutants_add', mutant_id)
        assert isdir(mutant_dir), 'mutant_dir not found: ' + mutant_dir
        mutant_file, rela_path = find_mutant_file(mutant_dir)
        d4j_proj.clean()
        d4j_proj.apply_mutant_file(mutant_file, rela_path)
        print('mutant file applied: ' + mutant_file)
        print('relative path: ' + rela_path)
        try:
            d4j_proj.compile()
        except:
            compile_error_msg = "compile error for mutant " + d4j_proj.project_id + mutant_id
            print(compile_error_msg)
            compile_error_file = join(compile_err_dir, fix_proj_1f(d4j_proj.project_id + '-1f') + '.txt')
            with open(compile_error_file, 'a') as f:
                f.write(mutant_id + '\n')
            continue
        try:
            d4j_proj.test(timeout=timeout)
        except subprocess.CalledProcessError:
            time_out_msg = "test timeout after {} seconds for {}".format(timeout, d4j_proj.project_id + mutant_id)
            print(time_out_msg)
            timeout_file = join(timeout_dir, fix_proj_1f(d4j_proj.project_id + '-1f') + '.txt')
            with open(timeout_file, 'a') as f:
                f.write(mutant_id + '\n')
            continue
        
        failing_tests = extract_failing_tests(d4j_proj.failing_tests_path)
        if len(failing_tests) == 0:
            print('no failing tests for mutant ' + d4j_proj.project_id + mutant_id)
            no_fail_file = join(no_fail_dir, fix_proj_1f(d4j_proj.project_id + '-1f') + '.txt')
            with open(no_fail_file, 'a') as f:
                f.write(mutant_id + '\n')
            continue
        output_file = join(output_root_dir, fix_proj_1f(d4j_proj.project_id + '-1f'), mutant_id + '.txt')
        mkdir_for_file(output_file)
        with open(output_file, 'w') as f:
            for test in failing_tests:
                f.write(test + '\n')
                
if __name__ == '__main__':
    print('usage: python get_failing_tests.py [d4j|d4j_add] [project_id] [mutant_id]')
    mode = sys.argv[1]
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    dataset_config = load_config(join(get_config_dir(), 'common', 'dataset.yaml'))
    prapr_config = load_config(join(get_config_dir(), 'prapr', 'mutants.yaml'))
    
    if len(sys.argv) > 3:
        specified_proj_id = sys.argv[2]
        specified_mutant_id = sys.argv[3]
    elif len(sys.argv) > 2:
        specified_proj_id = sys.argv[2]
        specified_mutant_id = None
    else:
        specified_proj_id = None
        specified_mutant_id = None
    repo_root_dir = common_config['validation_repo_root_dir']
    mutant_root_dir = common_config['mutant_root_dir']
    if mode == 'd4j':
        projects = all_projects
        sample_id_dir = rela_to_abs_path(dataset_config['sample_1700_list_dir'])
        failing_tests_dir = rela_to_abs_path(prapr_config['failing_tests_root_dir'])
    elif mode == 'd4j_add':
        projects = add_projects
        sample_id_dir = rela_to_abs_path(dataset_config['sample_1700_add_list_dir'])
        failing_tests_dir = rela_to_abs_path(prapr_config['failing_tests_add_root_dir'])
    timeout_dir = join(failing_tests_dir, 'timeout')
    compile_err_dir = join(failing_tests_dir, 'compile_error')
    no_fail_dir = join(failing_tests_dir, 'no_fail')
    timeout = 300
    for project in projects:
        if specified_proj_id is not None and project != specified_proj_id: continue
        bug_id = '13' if project == 'mockito' else '25' if project == 'collections' else '1'
        d4j_proj = Defects4J(project, bug_id, 'fixed', join(repo_root_dir, project))
        d4j_proj.checkout_if_not_exist_or_empty()
        d4j_proj.clean()
        record_failing_tests_for_mutants(d4j_proj, join(mutant_root_dir, fix_proj_1f(project + '-1f')),\
            file_to_ids(join(sample_id_dir, fix_proj_1f(project + '-1f'), 'sampledMutIds.txt')),\
                failing_tests_dir, timeout_dir, \
                    compile_err_dir, no_fail_dir, specified_mutant_id=specified_mutant_id)
        