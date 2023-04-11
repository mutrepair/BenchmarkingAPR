import os,sys, re
from os.path import *
from utils import fix_proj_1f

def load_mutants(proj:str, mutant_root_dir:str):
    mutants_log_path = join(mutant_root_dir, fix_proj_1f(proj + '-1f'), 'mutants.log')
    mutants = {}
    with open(mutants_log_path) as f:
        lines = f.readlines()
        for line in lines:            
            # match line number
            line = line.strip()
            count = 0
            for match in re.finditer(r":[0-9]+:", line):
                count += 1
                line_no = int(match.group()[1:-1])
                start = match.start()
                end = match.end()
            assert count >= 1, mutants_log_path + '\t' + line
            
            line_head = line[:start]
            line_tail = line[end:]
            try:
                items = line_head.strip().split(':')
                [mid, operator] = items[:2]
                [rep_symbol, method_full_name] = items[-2:]
                ori_symbol = ':'.join(items[2:-2])
            except:
                print(line_head)
                raise Exception
            change = line_tail
            
            # # Currently we skip deletion mutation
            # if operator == 'STD': continue

            full_class_name = method_full_name.split('@')[0]
            main_class_name = full_class_name.split('$')[0]
            mutated_file_path = main_class_name.replace('.', '/') + '.java'
            mutants[mid] = dict()
            mutants[mid]['operator'] = operator
            mutants[mid]['ori_symbol'] = ori_symbol
            mutants[mid]['rep_symbol'] = rep_symbol
            mutants[mid]['method_full_name'] = method_full_name
            mutants[mid]['line_no'] = line_no
            mutants[mid]['change'] = change
            mutants[mid]['class_name'] = main_class_name
            mutants[mid]['full_class_name'] = full_class_name
            mutants[mid]['path'] = mutated_file_path
    
    return mutants