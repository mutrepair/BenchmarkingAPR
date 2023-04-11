import os,sys
from os.path import *
sys.path.append(os.path.abspath(__file__ + '/../'))
from d4j_info import load_active_bug_ids

if __name__ == '__main__':
    d4j_home_dir = os.getenv('DEFECTS4J_HOME')
    assert isdir(d4j_home_dir), d4j_home_dir + ' does not exist'
    src_patch_root_dir = join(d4j_home_dir, 'framework', 'projects')
    active_bug_ids = load_active_bug_ids()
    output_path = join(abspath(dirname(__file__)), pardir, 'resources', 'single_line_bug_list.txt')
    single_line_bug_list = []
    
    for proj in os.listdir(src_patch_root_dir):
        proj_dir = join(src_patch_root_dir, proj)
        if not isdir(proj_dir):
            continue
        if proj == 'lib':
            continue
        patch_dir = join(proj_dir, 'patches')
        
        for file in os.listdir(patch_dir):
            if file.endswith('.src.patch'):
                patch_id = file.split('.')[0]
                if not patch_id in active_bug_ids[proj.lower()]:
                    continue
                patch_file_path = join(patch_dir, file)
                with open(patch_file_path, encoding='ISO-8859-1') as f:
                    lines = f.readlines()
                
                added_lines = []
                removed_lines = []
                for line in lines[5:]:
                    if line.startswith('+'):
                        added_lines.append(line)
                    elif line.startswith('-'):
                        removed_lines.append(line)
                if len(added_lines) == 1 and len(removed_lines) == 1:
                    single_line_bug_list.append(proj.lower() + '_' + patch_id)
    
    v1_2_cnt = 0
    v_2_0_add_cnt = 0
    single_line_bug_list.sort()
    with open(output_path, 'w') as f:
        for bug in single_line_bug_list:
            f.write(bug + '\n')
            proj, id = bug.split('_')
            if proj in ['chart', 'closure', 'lang', 'math', 'time', 'mockito']:
                v1_2_cnt += 1
            else:
                v_2_0_add_cnt += 1
                
    print('v1.2: %d, v2.0+: %d' % (v1_2_cnt, v_2_0_add_cnt))