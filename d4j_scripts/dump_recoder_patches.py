import shutil
import subprocess
import os, sys, re
from os.path import *
from major_report_parser import load_mutants
import javalang
from javalang.ast import Node
from config_loader import load_config
from utils import get_config_dir, rela_to_abs_path, fix_proj_1f

sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import *

def extract_method(src_code:str, method_lineno:int):
    lines = src_code.split('\n')
    start = method_lineno
    end = start + 1
    while (end <= len(lines) + 1):
        content = '\n'.join(lines[start - 1 : end - 1])
        if content.strip().endswith('}') and content.count('{') == content.count('}'):
            return start, end
        else:
            end += 1

    raise Exception

def extract_method_line(src_code:str, lineno:int, id:str):
    # lineno starts from 1
    
    # javalang bug for chart31999
    if id == 'lang4694': return 535
    if id == 'closure49179': return 416
    tree = javalang.parse.parse(src_code)
    for decl in [javalang.tree.MethodDeclaration, javalang.tree.ConstructorDeclaration]:
        for _, node in tree.filter(decl):
            for _, child in node:
                start = node.position[0]
                if child.position != None:
                    end = child.position[0]
                    # print('start: {}'.format(start))
                    # print('end: {}'.format(end))
                    if start <= lineno and end >= lineno:
                    # if child.position[0] == lineno:
                        return node.position[0]
    
    raise Exception

def get_file(dir_path):
    return subprocess.run(['find', dir_path, '-name', '*.java'], stdout=subprocess.PIPE).stdout.decode().strip()

def file_to_str(file:str):
    with open(file) as f:
        content = f.read()
    return content

if __name__ == '__main__':
    mode = sys.argv[1]
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    dataset_config = load_config(join(get_config_dir(), 'common', 'dataset.yaml'))
    if mode == 'd4j':
        recoder_config = load_config(join(get_config_dir(), 'recoder', 'recoder_sample_1700.yaml'))
        projects = all_projects
        sampled_mutant_id_dir_path = rela_to_abs_path(dataset_config['sample_1700_list_dir'])
    elif mode == 'd4j_add':
        recoder_config = load_config(join(get_config_dir(), 'recoder', 'recoder_sample_1700_add.yaml'))
        projects = add_projects
        sampled_mutant_id_dir_path = rela_to_abs_path(dataset_config['sample_1700_add_list_dir'])
        
    method_patch_root_dir = rela_to_abs_path(recoder_config['method_patch_dir'])
    patch_output_root_dir_path = rela_to_abs_path(recoder_config['src_patch_dir'])
    mutant_root_dir_path = rela_to_abs_path(common_config['mutant_root_dir'])
    repo_root_dir = rela_to_abs_path(common_config['validation_repo_root_dir'])
    
    for proj in projects:
        proj_dir = join(repo_root_dir, proj)
        bug_id = '13' if proj == 'mockito' else '25' if proj == 'collections' else '1'
        d4j_proj = Defects4J(proj, bug_id, 'fixed', proj_dir)
        d4j_proj.checkout_if_not_exist_or_empty()
        sampled_mutant_file_path = join(sampled_mutant_id_dir_path, fix_proj_1f(proj + '-1f'), 'sampledMutIds.txt')
        sampled_ids = file_to_ids(sampled_mutant_file_path)
        mutants = load_mutants(proj, mutant_root_dir_path)
        for mutant_id in sampled_ids:
            mutant = mutants[mutant_id]
            line_no = mutant['line_no']
            src_rela_path = mutant['path']
            src_file_path = join(proj_dir, get_fst_v_src_rela_dir(proj), src_rela_path)
            assert isfile(src_file_path), src_file_path
            content = file_to_str(src_file_path)
            
            method_start_line = extract_method_line(content, line_no, proj + mutant_id)
            fixed_method_start, fixed_method_end = extract_method(content, method_start_line)
            assert fixed_method_start == method_start_line, 'method start line number not match!'
            
            lines = content.split('\n')
            content_before_method = lines[:fixed_method_start - 1]
            content_after_method = lines[fixed_method_end - 1:]
            
            patch_dir_path = join(method_patch_root_dir, proj + mutant_id)
            if not isdir(patch_dir_path):
                print('patch dir not exist: ' + patch_dir_path)
                continue
            
            for patch_file in os.listdir(patch_dir_path):
                patch_file_path = join(patch_dir_path, patch_file)
                patch_id = patch_file.split('.')[0]
                patch_method_content = file_to_str(patch_file_path)
                content_after_patch = content_before_method + patch_method_content.split('\n') \
                    + content_after_method
                patch_output_file_path = mkdir_for_file(join(patch_output_root_dir_path, proj, \
                    mutant_id, patch_id + '.java'))
                with open(patch_output_file_path, 'w') as f:
                    f.writelines(line + '\n' for line in content_after_patch)