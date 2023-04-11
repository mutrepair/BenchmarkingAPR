import os,sys
sys.path.append(os.path.abspath(__file__ + '/../'))
from os.path import *
from defects4j import *
from major_report_parser import *
from utils import fix_proj_1f, rela_to_abs_path, get_config_dir
from config_loader import load_config

def show_bug(d4j_proj:Defects4J, mutant_id:str):
    proj = d4j_proj.project_id
    mutant_dir = join(mutant_root_dir, fix_proj_1f(proj + '-1f'), 'mutants', mutant_id)
    assert isdir(mutant_dir), "Mutant dir not found: " + mutant_dir
    file = mutant_dir
    while (not isfile(file)) or (not file.endswith('.java')):
        file = join(file, os.listdir(file)[0])
        rela_path = relpath(file, mutant_dir)
    assert isfile(file) and file.endswith('.java')
    src_rela_dir = get_fst_v_src_rela_dir(proj)
    ori_file_path = join(repo_root_dir, proj, src_rela_dir, rela_path)
    assert isfile(ori_file_path)
    print("Mutant Operator: " + mutants[mutant_id]['operator'])
    os.system('diff -u -w ' + ori_file_path + ' ' + file)
    
if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    proj = sys.argv[1]
    mutant_id = sys.argv[2]
    mutant_root_dir = rela_to_abs_path(common_config['mutant_root_dir'])
    repo_root_dir = rela_to_abs_path(common_config['validation_repo_root_dir'])
    bug_id = '13' if proj == 'mockito' else '25' if proj == 'collections' else '1'
    d4j_proj = Defects4J(proj, bug_id, 'fixed', join(repo_root_dir, proj))
    d4j_proj.clean()
    mutants = load_mutants(d4j_proj.project_id, mutant_root_dir)
    show_bug(d4j_proj, mutant_id)