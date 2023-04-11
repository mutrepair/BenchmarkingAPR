import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../'))
import re
from utils import rela_to_abs_path, get_config_dir
from config_loader import load_config

def parse_log(file, proj):
    with open(file, 'r') as f:
        lines = f.readlines()
        content = ''.join(lines)
        if len(lines) <= 2 and lines[0].strip().endswith('OK'):
            return "success"
        elif len(lines) <= 2 and not lines[0].strip().endswith('OK'):
            return "timeout"
        else:
            if proj == 'time':
                if "Exception" in content or "Error" in content:
                    for line in lines:
                        if ("Exception" in line or "Error" in line) and "[javac]" not in line and "[java]" not in line:
                            result = ":".join(line.split(':')[2:]).strip()
                            return result
            assert "error:" in content or "Killed" in content, file
            for line in lines:
                if "error:" in line:
                    result = line.split('error:')[1].strip()
                    break
                elif "Killed" in line:
                    return "timeout"
        return result
    
def count_compilable(result_dict):
    compilable = 0
    timeout = 0
    error = 0
    for bug_id in result_dict:
        for patch_id in result_dict[bug_id]:
            if result_dict[bug_id][patch_id] == 'success':
                compilable += 1
            elif result_dict[bug_id][patch_id] == 'timeout':
                timeout += 1
            else:
                error += 1
    print('compilable: %d, timeout: %d, error: %d' % (compilable, timeout, error))
    
def count_non_compilable_per_bug(result_dict):
    bugs_with_no_compilable = []
    for bug_id in result_dict:
        compilable = 0
        timeout = 0
        error = 0
        for patch_id in result_dict[bug_id]:
            if result_dict[bug_id][patch_id] == 'success':
                compilable += 1
            elif result_dict[bug_id][patch_id] == 'timeout':
                timeout += 1
            else:
                error += 1
        if compilable == 0:
            bugs_with_no_compilable.append(bug_id)
    print('bugs with no compilable patches: %d' % len(bugs_with_no_compilable))
    print(bugs_with_no_compilable)
    
def refine_symptom(symptom:str) -> str:
    other_pattern_group = []
    other_pattern_group.append(r"variable .* might not have been initialized")
    other_pattern_group.append(r"variable .* is already defined in")
    other_pattern_group.append(r"constructor .* is already defined in")
    other_pattern_group.append(r"has private access in ")
    other_pattern_group.append(r"reference to .* is ambiguous")
    other_pattern_group.append(r"has protected access in")
    other_pattern_group.append(r"package .* does not exist")
    other_pattern_group.append(r"cannot be dereferenced")
    other_pattern_group.append(r"non-static .* cannot be referenced from a static context")
    other_pattern_group.append(r"no suitable (method|constructor) found for")
    other_pattern_group.append(r"Exception")
    other_pattern_group.append(r"(method|constructor) .* cannot be applied to given types")
    other_pattern_group.append(r"integer number too large")
    other_pattern_group.append(r"incompatible types")
    other_pattern_group.append(r"incomparable types")
    other_pattern_group.append(r"not supported in -source")
    other_pattern_group.append(r"cannot infer type arguments for")
    other_pattern_group.append(r"cannot assign a value to final variable")
    other_pattern_group.append(r"bad operand .* for (binary|unary) operator")
    other_pattern_group.append(r"array required, but .* found")
    other_pattern_group.append(r".* expected")
    other_pattern_group.append(r"method .* is already defined in class")
    other_pattern_group.append(r"undefined label")
    other_pattern_group.append(r"abstract method .* cannot be accessed directly")
    other_pattern_group.append(r"illegal character")
    other_pattern_group.append(r"is abstract; cannot be instantiated")
    other_pattern_group.append(r"final parameter .* may not be assigned")
    other_pattern_group.append(r"variable .* might already have been assigned")
    other_pattern_group.append(r"cannot reference .* before supertype constructor has been called")
    other_pattern_group.append(r"wrong number of type arguments")
    other_pattern_group.append(r"cannot reference .* before supertype constructor has been called")
    other_pattern_group.append(r"is not abstract and does not override abstract method")
    other_pattern_group.append(r".* in .* cannot override .* in .*")
    other_pattern_group.append(r".* cannot implement .*")
    other_pattern_group.append(r"type .* does not take parameters")
    other_pattern_group.append(r"llegal combination of modifiers")
    other_pattern_group.append(r"not an enclosing class")
    # refined_symptom = symptom
    for pattern in other_pattern_group:
        regex = re.compile(pattern)
        result = regex.search(symptom)
        if result: return pattern
    return symptom

def print_dict(dic:dict):
    for k in dic:
        print('%s: %d' % (k, dic[k]))

def merge_dict(dic:dict):
    new_dic = dict()
    for symptom in dic:
        num = dic[symptom]
        refined_symptom = refine_symptom(symptom)
        new_dic[refined_symptom] = new_dic.get(refined_symptom, 0) + num

    return new_dic

def count_compilation_error(result_dict):
    symptoms = dict()
    for bug_id in result_dict:
        for patch_id in result_dict[bug_id]:
            symptom = result_dict[bug_id][patch_id]
            if symptom != 'success':
                symptoms[symptom] = symptoms.get(symptom, 0) + 1
    symptoms = merge_dict(symptoms)
    symptoms = {k: v for k, v in sorted(symptoms.items(), key=lambda item: item[1], reverse=True)}
    print_dict(symptoms)
    
if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    compile_log_root_dir = rela_to_abs_path(common_config['compile_log_root_dir'])
    # tool = sys.argv[1]
    tools = ['coconut', 'cure', 'edits', 'recoder', 'reward_repair', 'selfapr', 'sequencer', 'tbar', 'tufano', 'alpha_repair', 'simfix']
    symptoms_all_dict = dict()
    for tool in tools:
        print('\nAnalyzing %s' % tool)
        result_dict = dict()
        for file in os.listdir(join(compile_log_root_dir, tool)):
            if file.endswith('.log'):
                proj, mutant_id, patch_id = file.split('.')[0].split('-')
                result = parse_log(join(compile_log_root_dir, tool, file), proj)
                bug_id = proj + '-' + mutant_id
                if not bug_id in result_dict:
                    result_dict[bug_id] = dict()
                result_dict[bug_id][patch_id] = result
                symptoms_all_dict[result] = symptoms_all_dict.get(result, 0) + 1
        count_compilable(result_dict)
        count_non_compilable_per_bug(result_dict)
        count_compilation_error(result_dict)

    symptoms_all_dict = merge_dict(symptoms_all_dict)
    print('All tools')
    print_dict({k: v for k, v in sorted(symptoms_all_dict.items(), key=lambda item: item[1], reverse=True)})