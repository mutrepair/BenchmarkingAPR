import shutil
import subprocess
import os, sys, re
from os.path import *
import javalang
from javalang.ast import Node
from pathlib import Path

def getProjDirName(projName: str):
    if projName == 'mockito':
        return projName + '-13f'
    elif projName == 'collections':
        return projName + '-25f'
    return projName + '-1f'

def get_sample_ids(mutants_root_dir:str, projs:list):
    sample_ids = []
    for proj in projs:
        proj_dir = join(mutant_root_dir, getProjDirName(proj))
        sample_id_file = join(proj_dir, 'sampledMutIds.txt')
        with open(sample_id_file) as f:
            lines = f.readlines()
        for line in lines:
            sample_ids.append(proj + line.strip())
    return sample_ids

def load_mutants(proj:str):
    mutant_dir = join(mutant_root_dir, getProjDirName(proj), 'mutants')
    mutants_log_path = join(mutant_root_dir, getProjDirName(proj), 'mutants.log')
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
                # [mid, operator, ori_symbol, rep_symbol, method_full_name] = line_head.strip().split(':')
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

            class_name = method_full_name.split('@')[0]
            if '$' in class_name:
                class_name = class_name.split('$')[0]
            mutated_file_path = class_name.replace('.', '/') + '.java'
            mutants[mid] = dict()
            mutants[mid]['operator'] = operator
            mutants[mid]['ori_symbol'] = ori_symbol
            mutants[mid]['rep_symbol'] = rep_symbol
            mutants[mid]['method_full_name'] = method_full_name
            mutants[mid]['line_no'] = line_no
            mutants[mid]['change'] = change
            mutants[mid]['path'] = mutated_file_path
    
    return mutants

def extract_method(src_code:str, method_lineno:int):
    lines = src_code.split('\n')
    start = method_lineno
    end = start + 1
    while (end <= len(lines) + 1):
        content = '\n'.join(lines[start - 1 : end - 1])
        if content.strip().endswith('}') and content.count('{') == content.count('}'):
            return content
        else:
            end += 1

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

def get_buggy_file(dir_path):
    return subprocess.run(['find', dir_path, '-name', '*.java'], stdout=subprocess.PIPE).stdout.decode().strip()

def get_line(file:str, lineno:int):
    with open(file) as f:
        lines = f.readlines()
    return lines[lineno - 1]

def file_to_str(file:str):
    with open(file) as f:
        content = f.read()
    return content

buggyMethodRange = {}
def make_ids(proj:str, mutants_dir:str):
    assert isdir(mutants_dir), mutants_dir
    ids_path = join(ids_info_dir, all_ids)
    mutants = load_mutants(proj)

    for i in mutants.keys():
        id = proj + str(i)
        if not id in sample_ids: continue
        method_full_name = mutants[str(i)]['method_full_name']
        buggy_line_no = int(mutants[str(i)]['line_no'])
        # recoder can not take care of bugs in fields so we should exclude them
        if ('@' not in method_full_name):
            print('bug not in method: ' + id)
            continue

        if isfile(join(ids_info_dir, 'buggy_methods', id + '.txt')): continue
                  
        buggy_class_file = get_buggy_file(join(mutants_dir, str(i)))
        assert isfile(buggy_class_file), mutants_dir + '\t' + buggy_class_file
        fixed_class_file = join(mutant_root_dir, getProjDirName(proj), src_rela_dir[proj], \
            relpath(buggy_class_file, join(mutants_dir, str(i))))
        assert isfile(fixed_class_file), mutants_dir + '\t' + fixed_class_file
        with open(ids_path, 'a') as f:
            f.write(id + '\n')

        shutil.copy(buggy_class_file, join(ids_info_dir, 'buggy_classes', id + '.java'))
        shutil.copyfile(fixed_class_file, join(ids_info_dir, 'fix_classes', id + '.java'))
        
        with open(join(ids_info_dir, 'buggy_lines', id + '.txt'), 'w') as f:
            f.write(get_line(buggy_class_file, buggy_line_no) + '\n')
        with open(join(ids_info_dir, 'fix_lines', id + '.txt'), 'w') as f:
            f.write(get_line(fixed_class_file, buggy_line_no) + '\n')

        buggy_class_content = file_to_str(buggy_class_file)
        fixed_class_content = file_to_str(fixed_class_file)
        print(id)
        print(buggy_class_file)
        print(fixed_class_file)
        # buggy_method_line_no = extract_buggy_method_line(buggy_class_content, buggy_line_no)
        if proj == 'lang' and (i == '4661' or i == '4694'):
            buggy_method_line_no = extract_buggy_method_line(fixed_class_content, buggy_line_no, need_offset=True)
        elif proj == 'closure' and i == '49179':
            buggy_method_line_no = 416
        else:
            buggy_method_line_no = extract_buggy_method_line(fixed_class_content, buggy_line_no)
        assert buggy_method_line_no <= buggy_line_no, mutants_dir + '\t' + id
        rel_line_no = buggy_line_no - buggy_method_line_no
        rel_line_range = '[' + str(rel_line_no) + ':' + str(rel_line_no + 1) + ']'
        buggy_method = extract_method(buggy_class_content, buggy_method_line_no)
        buggy_line = get_line(fixed_class_file, buggy_line_no)
        buggy_method_lines = buggy_method.split('\n')
        # fixed_method = '\n'.join(buggy_method_lines[:rel_line_no] + [buggy_line.replace('\n', '')] + buggy_method_lines[rel_line_no + 1:])
        fixed_method = extract_method(fixed_class_content, buggy_method_line_no)

        buggyMethodEndLineNum = extract_method_end_line(fixed_class_content, buggy_method_line_no)
        buggyMethodRange[id] = (buggy_method_line_no, buggyMethodEndLineNum)
        print((buggy_method_line_no, buggyMethodEndLineNum))

        with open(join(ids_info_dir, 'buggy_methods', id + '.txt'), 'w') as f:
            f.write(buggy_method)
        with open(join(ids_info_dir, 'fix_methods', id + '.txt'), 'w') as f:
            f.write(fixed_method)
        
        meta_content = '<sep>'.join(['mutapr', id, rel_line_range, rel_line_range, 
        buggy_class_file + '@' + buggy_method_lines[0].strip().replace('{', '').replace('}', '')])
        with open(join(ids_info_dir, 'metas', id + '.txt'), 'w') as f:
            f.write(meta_content)

if __name__ == '__main__':
    # proj = sys.argv[1]
    src_rela_dir = {"chart": "source", "cli": "src/java", "closure": "src", "codec": "src/java", "collections": "src/main/java", "compress": "src/main/java", "csv": "src/main/java",\
        "gson": "gson/src/main/java", "jacksoncore": "src/main/java", "jacksondatabind": "src/main/java", "jacksonxml": "src/main/java", \
        "jsoup": "src/main/java", "jxpath": "src/java", "lang": "src/main/java", "math": "src/main/java", "mockito": "src", "time": "src/main/java"}
    mutant_root_dir = '../dataset/d4jProj/'
    projects = src_rela_dir.keys()
    sample_ids_file = 'ids_all_info/sample_1700.ids'    
    Path("ids_all_info").mkdir(exist_ok=True)
    sample_ids = get_sample_ids(mutant_root_dir, projects)

    projects = ['time']
    sample_ids_file = 'ids_all_info/sample_2.ids'    
    sample_ids = ['time4793', 'time3289']

    projects = ['collections', 'time', 'math', 'codec', 'chart', 'jacksondatabind', 'closure', 'jacksonxml', 'jxpath', 'jacksoncore', 'compress', 'lang']
    sample_ids_file = 'ids_all_info/sample_distribution+time2.ids'    
    sample_ids = ['collections6152', 'collections8112', 'collections8343', 'collections8216', 'collections8348', 'collections8108', 'collections10087', 'collections6148', 'collections8492', 'collections11864', 'collections2652', 'collections1508', 'time18136', 'time10081', 'time15493', 'time4864', 'time658', 'time12449', 'time2556', 'time19225', 'time10160', 'time2551', 'time4856', 'time8960', 'time11572', 'math94078', 'math118483', 'math94440', 'math108308', 'math118180', 'math94376', 'math115465', 'math41781', 'math94185', 'math117598', 'math115827', 'math94174', 'math92468', 'math109334', 'math118484', 'math94153', 'math117670', 'math108457', 'math107938', 'math118195', 'math115826', 'math35228', 'math16011', 'math118026', 'math27082', 'math1872', 'math35785', 'math39490', 'math116617', 'math95878', 'math35937', 'math111244', 'math110625', 'math39136', 'math74852', 'math110955', 'math64974', 'math63972', 'math22130', 'math7710', 'math116154', 'math101658', 'math110936', 'math117041', 'math84963', 'math35938', 'math109722', 'math38034', 'math111968', 'math17424', 'math15714', 'math34840', 'math41844', 'math60729', 'math95820', 'math57451', 'math117283', 'math81535', 'math19560', 'math117420', 'math117580', 'math111278', 'math103223', 'math34656', 'math119014', 'math19561', 'math35959', 'math5010', 'math108351', 'math27140', 'math113518', 'math48177', 'math108453', 'math32583', 'math111085', 'math111050', 'math103968', 'math96327', 'math77143', 'math58288', 'math116783', 'math14349', 'math42114', 'math15302', 'math14347', 'math80734', 'math36958', 'math8954', 'math16176', 'math3463', 'math62416', 'math118638', 'math42068', 'math94205', 'math117666', 'math94240', 'math94373', 'math93203', 'math66445', 'math118059', 'math14098', 'math108626', 'math18388', 'math118366', 'math118158', 'math62404', 'math94364', 'math59422', 'math93255', 'math118550', 'math108819', 'math2977', 'codec288', 'codec691', 'codec690', 'codec669', 'codec652', 'codec1017', 'codec497', 'codec4217', 'codec261', 'codec297', 'codec469', 'codec316', 'chart70393', 'chart70533', 'chart34236', 'chart76686', 'chart34237', 'chart54929', 'jacksondatabind927', 'jacksondatabind912', 'jacksondatabind7353', 'jacksondatabind40', 'closure48813', 'closure31081', 'closure31096', 'closure13793', 'closure30680', 'closure13710', 'closure36346', 'closure90', 'closure13942', 'closure30743', 'closure31012', 'closure13724', 'closure13866', 'closure30845', 'closure63', 'closure36345', 'closure144', 'closure13957', 'closure48224', 'closure11209', 'closure36349', 'closure30923', 'closure30740', 'closure11445', 'closure33315', 'closure31023', 'closure30953', 'closure30739', 'closure48791', 'closure30709', 'closure30952', 'jacksonxml292', 'jxpath12215', 'jxpath9840', 'jxpath7840', 'jxpath10391', 'jxpath10689', 'jxpath10804', 'jxpath8246', 'jxpath9432', 'jxpath10053', 'jxpath7850', 'jxpath10309', 'jxpath7922', 'jacksoncore16309', 'jacksoncore1370', 'jacksoncore13101', 'jacksoncore15722', 'jacksoncore5817', 'jacksoncore14170', 'jacksoncore15781', 'jacksoncore10690', 'jacksoncore13052', 'jacksoncore621', 'jacksoncore15840', 'jacksoncore10484', 'jacksoncore1806', 'jacksoncore15991', 'jacksoncore15886', 'jacksoncore15841', 'jacksoncore10570', 'jacksoncore11378', 'jacksoncore345', 'jacksoncore4570', 'jacksoncore15799', 'jacksoncore13888', 'jacksoncore10988', 'jacksoncore10606', 'jacksoncore10516', 'jacksoncore2147', 'jacksoncore5447', 'jacksoncore1367', 'jacksoncore103', 'jacksoncore12131', 'jacksoncore290', 'jacksoncore1366', 'jacksoncore12861', 'jacksoncore4441', 'jacksoncore11980', 'jacksoncore11868', 'jacksoncore834', 'jacksoncore6772', 'jacksoncore11218', 'jacksoncore3267', 'jacksoncore16146', 'jacksoncore10463', 'jacksoncore12996', 'jacksoncore15778', 'jacksoncore7347', 'jacksoncore3278', 'jacksoncore14434', 'jacksoncore11219', 'jacksoncore6093', 'jacksoncore14104', 'jacksoncore14220', 'jacksoncore12062', 'compress5465', 'compress1623', 'compress4844', 'compress7964', 'compress5574', 'compress5847', 'compress4899', 'compress5371', 'compress6028', 'compress5858', 'compress6121', 'compress1504', 'compress4853', 'compress7940', 'compress4816', 'compress7966', 'compress6174', 'compress3497', 'compress1607', 'compress6128', 'lang7857', 'lang7790', 'lang7154', 'lang6308', 'lang7824', 'lang21179', 'lang15195', 'lang15107', 'lang14961', 'lang15151', 'lang17101', 'lang6724', 'lang6310', 'lang15181', 'lang5887', 'lang5886', 'lang7700', 'lang7879', 'lang3503', 'lang6726', 'lang16696', 'time4793', 'time3289']

    with open(sample_ids_file, 'w') as f:
        for id in sample_ids:
            f.write(id + '\n')
    all_ids = 'all.ids'
    ids_info_dir = 'ids_all_info'
    
    (Path(ids_info_dir) / "buggy_classes").mkdir(exist_ok=True)
    (Path(ids_info_dir) / "fix_classes").mkdir(exist_ok=True)
    (Path(ids_info_dir) / "buggy_lines").mkdir(exist_ok=True)
    (Path(ids_info_dir) / "fix_lines").mkdir(exist_ok=True)
    (Path(ids_info_dir) / "buggy_methods").mkdir(exist_ok=True)
    (Path(ids_info_dir) / "fix_methods").mkdir(exist_ok=True)
    (Path(ids_info_dir) / "metas").mkdir(exist_ok=True)

    for proj in projects:
        mutants_dir = join(mutant_root_dir, getProjDirName(proj), 'mutants')
        log_file = join(mutant_root_dir, getProjDirName(proj), 'mutants.log')
        make_ids(proj, mutants_dir)

    # print(str(buggyMethodRange))
    with open('/home/xxx/research/mutBench/npr4j/ids_all_info/buggy_methods_range.txt', 'w') as f:
        for key in buggyMethodRange:
            f.write("{} {} {}\n".format(key, buggyMethodRange[key][0], buggyMethodRange[key][1]))