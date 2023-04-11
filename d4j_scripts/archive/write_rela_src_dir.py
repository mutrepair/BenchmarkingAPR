import os, sys
from os.path import *
import shutil
sys.path.append(os.path.abspath(__file__ + '/../'))
from defects4j import Defects4J

projects = ['cli', 'codec', 'compress', 'csv', 'gson', 'jacksoncore', 'jacksondatabind', 'jacksonxml', 'jsoup', 'jxpath']
rela_src_info_root_dir = '/home/jun/research/dlapr/SimFix/d4j-info/src_path'
for project in projects:
    proj_home = '/home/jun/research/dlapr/repo/' + project + '/' + project + '_1_buggy'
    if isdir(proj_home):
        shutil.rmtree(proj_home)
    
    # bug_id = '13' if proj == 'mockito' else '25' if proj == 'collections' else '1'
    d4j_project = Defects4J(project, '1', 'fixed', proj_home)
    if not isdir(proj_home):
        d4j_project.checkout()
    src_rela_dir = d4j_project.get_rela_src_path()
    src_test_rela_dir = d4j_project.get_rela_src_tests_path()
    bin_rela_dir = d4j_project.get_rela_bin_path()
    bin_tests_rela_dir = d4j_project.get_fst_v_rela_bin_tests_path()
    rela_info_path = join(rela_src_info_root_dir, project, '1.txt')
    if not isfile(rela_info_path):
        os.makedirs(dirname(rela_info_path))
    with open(rela_info_path, 'w') as f:
        f.write(src_rela_dir + '\n')
        f.write(bin_rela_dir + '\n')
        f.write(src_test_rela_dir + '\n')
        f.write(bin_tests_rela_dir + '\n')