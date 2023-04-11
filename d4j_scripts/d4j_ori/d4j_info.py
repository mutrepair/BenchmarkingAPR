import os,sys
from os.path import *
from typing import Union
sys.path.append(os.path.abspath(__file__ + '/../../'))
from defects4j import get_identifier_from_lower_name
from utils import file_to_ids

def load_single_line_bug_ids():
    return file_to_ids(join(abspath(dirname(__file__)), '..', 'resources', 'single_line_bug_list.txt'))

def load_active_bug_ids():
    d4j_home_dir = os.getenv('DEFECTS4J_HOME')
    assert isdir(d4j_home_dir), d4j_home_dir + ' does not exist'
    src_patch_root_dir = join(d4j_home_dir, 'framework', 'projects')
    active_bug_ids = {}
    
    for proj in os.listdir(src_patch_root_dir):
        proj_dir = join(src_patch_root_dir, proj)
        if not isdir(proj_dir):
            continue
        if proj == 'lib':
            continue
        bug_csv_file = join(proj_dir, 'active-bugs.csv')
        with open(bug_csv_file) as f:
            lines = f.readlines()
        for line in lines[1:]:
            line = line.strip()
            if not proj.lower() in active_bug_ids:
                active_bug_ids[proj.lower()] = []
            active_bug_ids[proj.lower()].append(line.split(',')[0])
            
    return active_bug_ids

def get_oracle_patch_line(bug_id) -> Union[None, tuple]:
    # this only works for single line bug
    d4j_home_dir = os.getenv('DEFECTS4J_HOME')
    assert isdir(d4j_home_dir), d4j_home_dir + ' does not exist'
    src_patch_root_dir = join(d4j_home_dir, 'framework', 'projects')
    proj_name, id = bug_id.split('_')
    patch_path = join(src_patch_root_dir, get_identifier_from_lower_name(proj_name), 'patches', id + '.src.patch')
    with open(patch_path, encoding='ISO-8859-1') as f:
        lines = f.readlines()
    buggy_line_no = int(lines[4].split(' ')[1].split(',')[0][1:]) + 3
    for line in lines[5:]:
        if line.startswith('-'): # since this patch is used to convert fixed code to buggy code, the oracle patch line is the removed line
            return buggy_line_no, line[1:].strip()
    return None