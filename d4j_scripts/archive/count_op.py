import os, sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../'))
from utils import file_to_ids

op_count = {}
bugs_count = 0
mutants_root_dir = '/home/jun/research/dlapr/all_mutants'
for proj_1f in os.listdir(mutants_root_dir):
    proj_dir = join(mutants_root_dir, proj_1f)
    if not isdir(proj_dir) or not proj_dir.endswith('-1f'): continue
    mutants_log_path = join(proj_dir, 'mutants.log')
    sampled_path = join('/home/jun/research/dlapr/sample1700', proj_1f, 'sampledMutIds.txt')
    sampled_ids = file_to_ids(sampled_path)
    
    with open(mutants_log_path) as f:
        lines = f.readlines()
    for line in lines:
        items = line.strip().split(':')
        id = items[0]
        op = items[1]
        if id in sampled_ids:
            bugs_count += 1
            if not op in op_count.keys():
                op_count[op] = 1
            else:
                op_count[op] += 1
    
print(op_count)
print(bugs_count)