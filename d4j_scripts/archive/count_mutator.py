import os, sys
from os.path import *
from major_report_parser import load_mutants

mutator_map = {}
mutator_total_map = {}

all_mutants_dir = '/home/jun/research/dlapr/all_mutants'
sample_all_dir = '/home/jun/research/dlapr/sample1700'
for proj_1f in os.listdir(sample_all_dir):
    file_path = join(sample_all_dir, proj_1f, 'sampledMutIds.txt')
    proj_name = proj_1f.split('-')[0]
    
    mutants = load_mutants(proj_name, all_mutants_dir)
    for mid in mutants:
        op = mutants[mid]['operator']
        mutator_total_map[op] = mutator_total_map.get(op, 0) + 1
        
    with open(file_path, 'r') as f:
        lines = f.readlines()
    
    for id in lines:
        id = id.strip()
        op = mutants[id]['operator']
        mutator_map[op] = mutator_map.get(op, 0) + 1
        
# print(mutator_map)
print(mutator_total_map)
# sum of all mutants
print(sum(mutator_total_map.values()))