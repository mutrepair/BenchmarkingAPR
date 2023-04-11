import os
from os.path import *
import subprocess
import re

def mutant_has_failing_tests(proj_id, mutant_id, failing_tests_dir):
    failing_tests_path = join(failing_tests_dir, proj_id, mutant_id + '.txt')
    return isfile(failing_tests_path) and getsize(failing_tests_path) > 0

def file_to_ids(file_path):
    with open(file_path) as f:
        lines = f.readlines()
        return [line.strip() for line in lines]
    
def extract_sampled_bug_id_list_for_mutators(sampled_id_dir):
    sampled_id_list = []
    assert isabs(sampled_id_dir) and isdir(sampled_id_dir), 'sampled_id_dir must be an absolute path to a directory'
    for mutator_file in os.listdir(sampled_id_dir):
        sampled_ids_path = join(sampled_id_dir, mutator_file)
        sampled_id_list += file_to_ids(sampled_ids_path)
    
    return sampled_id_list

def extract_sampled_bug_id_list(sampled_id_dir):
    sampled_id_list = []
    assert isabs(sampled_id_dir) and isdir(sampled_id_dir), 'sampled_id_dir must be an absolute path to a directory'
    for directory in os.listdir(sampled_id_dir):
        proj = directory.split('-')[0]
        sampled_ids_path = join(sampled_id_dir, directory, 'sampledMutIds.txt')
        sampled_id_list += [proj + '_' + id for id in file_to_ids(sampled_ids_path)]
    
    return sampled_id_list
    
def find_mutant_file(mutant_dir):
    print(mutant_dir)
    assert isdir(mutant_dir), 'mutant_dir must be a directory'
    rela_file_path = subprocess.check_output('find . -name "*.java"', shell=True, universal_newlines=True, cwd=mutant_dir).strip()
    file = join(mutant_dir, rela_file_path)
    assert isfile(file), 'mutant file not found: ' + file
    return file, rela_file_path

def extract_failing_tests(failing_tests_path:str):
    assert isfile(failing_tests_path), 'failing_tests_file not found: ' + failing_tests_path
    failing_tests = []
    with open(failing_tests_path) as f:
        lines = f.readlines()
        for line in lines:
            if line.startswith('--- '):
                failing_tests.append(line.strip()[4:]) # example: org.apache.commons.lang3.time.FastDateParserTest::testParseTimeZone
    return failing_tests

def mkdir_if_not_exist(dir_path):
    if not isdir(dir_path):
        os.makedirs(dir_path)

def mkdir_for_file(file_path):
    mkdir_if_not_exist(dirname(file_path))
    return file_path
    
def get_diff(ori_file, patched_file, output_file, context=None, ignore_space=True):
    if context == None:
        if ignore_space:
            cmd = 'diff -u -w {0} {1}'.format(ori_file, patched_file)
        else:
            cmd = 'diff -u {0} {1}'.format(ori_file, patched_file)
        result = subprocess.run(cmd.split(' '), stdout=subprocess.PIPE).stdout.decode()
        with open(output_file, 'w') as f:
            f.write(result)
            
def file_empty(file_path):
    with open(file_path) as f:
        content = f.read()
    return content == ''

def get_line(file_path, line_no):
    with open(file_path) as f:
        lines = f.readlines()
    return lines[line_no - 1].strip()

def get_buggy_and_patched_line(buggy_file_path:str, patched_file_path:str, remove_comment=False):
    assert isfile(buggy_file_path), 'buggy_file_path not found: ' + buggy_file_path
    assert isfile(patched_file_path), 'patched_file_path not found: ' + patched_file_path
    output = subprocess.run(['diff', '-u', '-w', buggy_file_path, patched_file_path], stdout=subprocess.PIPE).stdout.decode()
    buggy_lines = []
    patched_lines = []
    for line in output.split('\n')[4:]:
        if line.startswith('+') and line[1:].strip() != '':
            if not remove_comment:
                patched_lines.append(line[1:])
            else:
                patched_lines.append(line[1:].split('//')[0])
        elif line.startswith('-') and line[1:].strip() != '':
            if not remove_comment:
                buggy_lines.append(line[1:])
            else:
                buggy_lines.append(line[1:].split('//')[0])
    
    return '\n'.join(buggy_lines), '\n'.join(patched_lines)

def save_list(file_path:str, item_list:list):
    if not isfile(file_path):
        os.makedirs(dirname(file_path), exist_ok=True)
    with open(file_path, 'w') as f:
        f.writelines([str(item) + '\n' for item in item_list])
        
def str_to_raw_str(string:str):
    return string.strip().replace(' ', '').replace('\t', '').replace('\n', '')

def file_to_raw_str(file):
    with open(file) as f:
        content = f.read()
    return content.strip().replace('\n','').replace('\t','').replace(' ', '')

def file_to_str(file):
    with open(file) as f:
        content = f.read()
    return content.strip()

def file_to_clean_raw_str(file):
    with open(file) as f:
        content = f.read()
    cleaned_content = remove_comments(content)
    return cleaned_content.replace(' ', '').replace('\t', '').replace('\n', '')

def remove_comments(content):
    res = re.sub(re.compile("/\*.*?\*/",re.DOTALL ) ,"" ,content) # remove all occurance streamed comments (/*COMMENT */) from string
    res = re.sub(re.compile("//.*?\n" ) ,"" , res) # remove all occurance singleline comments (//COMMENT\n ) from string
    return res

def get_root_dir():
    return abspath(dirname(dirname(abspath(__file__))))

def get_script_dir():
    return abspath(dirname(abspath(__file__)))

def get_config_dir():
    return join(abspath(dirname(abspath(__file__))), 'config')

def fix_proj_1f(proj_1f:str) -> str:
    return proj_1f.replace('collections-1f', 'collections-25f').replace('mockito-1f', 'mockito-13f')

def rela_to_abs_path(rela_path:str) -> str:
    return join(get_root_dir(), rela_path)