import shutil
import subprocess
import os, sys, re
from os.path import *
import javalang
from javalang.ast import Node

sys.path.append(os.path.abspath(__file__ + '/../../'))
from defects4j import *
from utils import *
from d4j_info import *
from config_loader import load_config

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

if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    recoder_config = load_config(join(get_config_dir(), 'recoder', 'recoder_d4j_ori.yaml'))
    method_patch_root_dir = rela_to_abs_path(recoder_config['method_patch_dir'])
    bug_id_list = load_single_line_bug_ids()
    buggy_repo_root_dir = rela_to_abs_path(common_config['d4j_buggy_repo_root_dir'])
    patch_output_root_dir = rela_to_abs_path(rela_to_abs_path(recoder_config['src_patch_dir']))
    for bug_id in bug_id_list:
        proj_name, id = bug_id.split('_')
        proj_dir = join(buggy_repo_root_dir, proj_name + '_' + id)
        d4j_proj = Defects4J(proj_name, id, 'buggy', proj_dir)
        d4j_proj.checkout_if_not_exist_or_empty()
        d4j_proj.clean()
        ori_src_file_path = d4j_proj.get_buggy_file_path()
        line_no = get_oracle_patch_line(bug_id)[0]
        assert isfile(ori_src_file_path), ori_src_file_path
        content = file_to_str(ori_src_file_path)
        
        method_start_line = extract_method_line(content, line_no, bug_id)
        if method_start_line == None:
            print("DEBUG: method start line not found for bug id: {}".format(bug_id))
            continue
        fixed_method_start, fixed_method_end = extract_method(content, method_start_line)
        assert fixed_method_start == method_start_line, 'method start line number not match'
        
        lines = content.split('\n')
        content_before_method = lines[:fixed_method_start - 1]
        content_after_method = lines[fixed_method_end - 1:]
        
        patch_dir_path = join(method_patch_root_dir, proj_name + id)
        if not isdir(patch_dir_path):
            print('patch dir not exist: ' + patch_dir_path)
            continue
        
        for patch_file in os.listdir(patch_dir_path):
            patch_file_path = join(patch_dir_path, patch_file)
            patch_id = patch_file.split('.')[0]
            patch_method_content = file_to_str(patch_file_path)
            content_after_patch = content_before_method + patch_method_content.split('\n') + content_after_method
            patch_output_file_path = mkdir_for_file(join(patch_output_root_dir, proj_name, id, patch_id + '.java'))
            with open(patch_output_file_path, 'w') as f:
                f.writelines(line + '\n' for line in content_after_patch)