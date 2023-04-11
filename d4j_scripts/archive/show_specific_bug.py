import os,sys
sys.path.append(os.path.abspath(__file__ + '/../'))
from os.path import *
from defects4j import *
from major_report_parser import *
from utils import *
import random

if __name__ == '__main__':
    tgt_operator = sys.argv[1]
    select_num = 5
    sampled_id_dir = '/home/jun/research/dlapr/sample1700'
    sampled_id_list = extract_sampled_bug_id_list(sampled_id_dir)
    mutant_root_dir = '/home/jun/research/dlapr/all_mutants'
    selected_bug_id = []
    loop_count = 0
    while len(selected_bug_id) < select_num:
        loop_count += 1
        if loop_count > 1000:
            print('cannot find desired bug ids')
            break
        bug_id = random.choice(sampled_id_list)
        sampled_id_list.remove(bug_id)
        proj, id = bug_id.split('_')
        if bug_id not in selected_bug_id:
            mutants = load_mutants(proj, mutant_root_dir)
            mutant = mutants[id]
            op = mutant['operator']
            if not op == tgt_operator: continue
            selected_bug_id.append(bug_id)
    
    for bug_id in selected_bug_id:
        print(bug_id)