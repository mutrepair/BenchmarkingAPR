import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import *
from utils import *
from major_report_parser import *
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
    mode = sys.argv[1]
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    dataset_config = load_config(join(get_config_dir(), 'common', 'dataset.yaml'))
    prapr_config = load_config(join(get_config_dir(), 'prapr', 'mutants.yaml'))
    repo_root_dir = rela_to_abs_path(common_config['validation_repo_root_dir'])
    mutant_root_dir = rela_to_abs_path(common_config['mutant_root_dir'])
    mutate_susp_stmt = 'PERFECT'
    pom_root_dir = rela_to_abs_path(prapr_config['pom_root_dir'])
    if mode == 'd4j':
        projects = all_projects
        failing_tests_root_dir = rela_to_abs_path(prapr_config['failing_tests_root_dir'])
        sample_id_dir = rela_to_abs_path(dataset_config['sample_1700_list_dir'])
        result_dir = rela_to_abs_path(prapr_config['result_dir'])
    elif mode == 'd4j_add':
        projects = add_projects
        failing_tests_root_dir = rela_to_abs_path(prapr_config['failing_tests_add_root_dir'])
        sample_id_dir = rela_to_abs_path(dataset_config['sample_1700_add_list_dir'])
        result_dir = rela_to_abs_path(dataset_config['result_add_dir'])
    else:
        raise Exception('Unknown mode: ' + mode)
    for project in projects:
        bug_id = '13' if project == 'mockito' else '25' if project == 'collections' else '1'
        d4j_proj = Defects4J(project, bug_id, 'fixed', join(repo_root_dir, project))
        src_rela_dir = get_fst_v_src_rela_dir(project)
        d4j_proj.checkout_if_not_exist_or_empty()
        mutants = load_mutants(project, mutant_root_dir)
        sampled_ids = file_to_ids(join(sample_id_dir, fix_proj_1f(project + '-1f'), 'sampledMutIds.txt'))
        
        result_proj_dir = join(result_dir, project)
        
        for mutant_id in sampled_ids:
            # handle compilation error and test time out
            result_for_mutant_dir = join(result_proj_dir, mutant_id)
            if isdir(result_for_mutant_dir) and len(os.listdir(result_for_mutant_dir)) > 0: # skip if already have result
                print('skip ' + mutant_id)
                continue
            if not mutant_has_failing_tests(project, mutant_id, failing_tests_root_dir):
                print('mutant ' + mutant_id + ' has no failing tests')
                sys.exit(1)
            else:
                d4j_proj.clean()
                d4j_proj.add_pom(src_pom_root_dir=pom_root_dir, tgt_pom_name='pom_for_prapr.xml')
        
                mutant = mutants[mutant_id]
                faulty_line = str(mutant['line_no'])
                faulty_statement_line = str(get_accurate_statement_line(join(repo_root_dir, project,\
                    src_rela_dir, mutant['path']), mutant['line_no']))
                faulty_class = mutant['full_class_name']
                failing_tests = file_to_ids(join(failing_tests_root_dir, project, mutant_id + '.txt'))
                
                d4j_proj.add_pom_with_perfect_fl(faulty_class, faulty_statement_line, failing_tests, \
                    src_pom_root_dir=pom_root_dir, \
                        tgt_pom_name='pom_for_prapr_perfect.xml')
                
                mutant_file, rela_path = find_mutant_file(join(mutant_root_dir, fix_proj_1f(project + '-1f'), 'mutants', mutant_id))
                d4j_proj.apply_mutant_file(mutant_file, rela_path)
                try:
                    d4j_proj.compile() # remember to compile both source code and test code!
                except subprocess.CalledProcessError:
                    print("compilation error for mutant " + project + mutant_id)
                    sys.exit(1)
                print('running prapr for mutant: ' + project + mutant_id)
                try:
                    d4j_proj.run_prapr(pom_name='pom_for_prapr_perfect.xml', mutate_susp_stmt=mutate_susp_stmt)
                except subprocess.CalledProcessError:
                    print("prapr run error for mutant: " + project + mutant_id)
                    sys.exit(1)
                
                prapr_report_dir = join(d4j_proj.proj_home, 'target', 'prapr-reports')
                if d4j_proj.project_id == 'gson':
                    prapr_report_dir = join(d4j_proj.proj_home, 'gson', 'target', 'prapr-reports')
                save_result(join(prapr_report_dir, os.listdir(prapr_report_dir)[0]), \
                    join(result_proj_dir, mutant_id))

        d4j_proj.clean()