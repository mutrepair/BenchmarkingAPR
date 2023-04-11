import javalang
from javalang.ast import Node
from pathlib import Path
import re, sys

# load the path of d4j_scripts
d4j_scripts_Path = Path('../../d4j_scripts').resolve()
sys.path.append(str(d4j_scripts_Path))
sys.path.append(str(d4j_scripts_Path / 'd4j_ori'))
from defects4j import *
from d4j_info import *


def extract_buggy_method_line(src_code:str, lineno:int, need_offset=False):
    # lineno starts from 1
    # javalang bug for chart31999
    print('line number: ' + str(lineno))
    tree = javalang.parse.parse(src_code)
    for decl in [javalang.tree.MethodDeclaration, javalang.tree.ConstructorDeclaration]:
        for _, node in tree.filter(decl):
            for _, child in node:
                start = node.position[0]
                if child.position != None:
                    end = child.position[0]
                    # print('start: {}'.format(start))
                    # print('end: {}'.format(end))
                    if need_offset:
                        start = start - 1
                        end = end - 1
                    if start <= lineno and end >= lineno:
                    # if child.position[0] == lineno:
                        return start
    raise Exception

def extract_method_end_line(src_code:str, method_lineno:int):  # the end line number is exluded
    lines = src_code.split('\n')
    start = method_lineno
    end = start + 1
    while (end <= len(lines) + 1):
        content = '\n'.join(lines[start - 1 : end - 1])
        if content.strip().endswith('}') and content.count('{') == content.count('}'):
            return end
        else:
            end += 1
    raise Exception

def getD4jBugLine(pid_bid):
    return get_oracle_patch_line(pid_bid)[0]

def file_to_str(file:str):
    with open(file) as f:
        content = f.read()
    return content

def genRange(target=Path('ids_all_info').resolve()):
    range = {}
    for buggySourceFile in (target/'buggy_classes').iterdir():
        m = re.match(r'(\w+?)(\d+)', buggySourceFile.stem)
        assert m is not None
        pid = m[1]
        bid = m[2]
        lineNum = getD4jBugLine(('{}_{}'.format(pid, bid)))
        content = file_to_str(str(buggySourceFile))
        buggyMethodLineNum = extract_buggy_method_line(content, lineNum)
        endLine = extract_method_end_line(content, buggyMethodLineNum)
        range[buggySourceFile.stem] = (buggyMethodLineNum, endLine)

    with open('/home/xxx/research/mutBench/npr4j/ids_all_info/buggy_methods_range.txt', 'w') as f:
        for key in range:
            f.write("{} {} {}\n".format(key, range[key][0], range[key][1]))

if __name__ == '__main__':
    genRange()