import sys, os, subprocess, csv
sys.path.append(os.path.abspath(__file__ + '/../../../d4j_scripts'))
from utils import *
from defects4j import *
from major_report_parser import *
from d4j_ori.d4j_info import load_single_line_bug_ids, get_oracle_patch_line
from d4j_ori.dump_buggy_oracle_patch import get_oracle_patch_path
from config_loader import load_config

if __name__ == '__main__':
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    self_apr_config = load_config(join(get_config_dir(), 'selfapr', 'd4j_ori.yaml'))
    bug_ids = load_single_line_bug_ids()
    buggy_repo_root_dir = rela_to_abs_path(common_config['d4j_buggy_repo_root_dir'])
    oracle_patch_dir = join(get_root_dir(), 'd4j_scripts/resources/d4j_ori_oracle_patches')
    jar_path = abspath(join(__file__, '../perturbation_model/target/perturbation-0.0.1-SNAPSHOT-jar-with-dependencies.jar'))
    input_path = rela_to_abs_path(self_apr_config['input_csv_path'])
    counter = 0
    
    with open(input_path, 'w') as f:
        f.write('bugid\tpatch\tbuggy\tid\tbuglineNo\tremoveNo\tfilepath\n')
    for bug_id in bug_ids:
        proj_name, id = bug_id.split('_')
        repo_dir = join(buggy_repo_root_dir, bug_id)
        d4j_proj = Defects4J(proj_name, id, 'buggy', repo_dir)
        d4j_proj.checkout_if_not_exist_or_empty()
        d4j_proj.clean()
        failing_test_method, fail_diag = d4j_proj.test_with_report()
        d4j_proj.clean()
        diagnosticMsg = '[FE] ' + fail_diag + ' ' + failing_test_method
        
        # code from SelfAPR/3_prepare_test_data.py
        className = d4j_proj.get_buggy_class_name().split('.')[-1]
        
        buggy_file_path = d4j_proj.get_buggy_file_path()
        fixed_file_path, file_rela_path = get_oracle_patch_path(oracle_patch_dir, bug_id)
        buggy_line, patched_line = get_buggy_and_patched_line(buggy_file_path, fixed_file_path)
        line_no = get_oracle_patch_line(bug_id)[0]
        hunk_info = str(line_no) + '[buggy]' + buggy_line + '[patch]' \
            + patched_line + '[buggyLineNo]' + str(1)
        startLineNo = hunk_info.split('[buggy]')[0]
        buggyLines = hunk_info.split('[buggy]')[1].split('[patch]')[0].strip().replace('\n', ' ')
        patchLines = hunk_info.split('[patch]')[1].split('[buggyLineNo]')[0].strip().replace('\n', ' ')
        bno = hunk_info.split('[buggyLineNo]')[1] # number of buggy lines
        
        print('targetfile'+buggy_file_path)
        print('startLineNo=========startLineNo====='+startLineNo)
        print('bugId=========bugId====='+bug_id)
        print('buggyLines'+buggyLines)
        cxt=''
        metaInfo='' 
        
        cmd = 'timeout 200 java -jar {} '.format(jar_path) +buggy_file_path+' test-'+startLineNo
        result = subprocess.run(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)
        print(result)
        result = str(result)
        if '[CLASS]' in result:
            metaInfo = result.split('[CLASS]')[-1]
        if 'startline:' in result:
            cxtStart=result.split('startline:')[1]
            cxtStart=cxtStart.split(' ')[0]
        else:
            cxtStart = int(startLineNo)-10
        if 'endline:' in result:
            cxtEnd=result.split('endline:')[1]
            if '\'' in cxtEnd:
                cxtEnd=cxtEnd.split('\'')[0]
            if '\"' in cxtEnd:
                cxtEnd=cxtEnd.split('\"')[0]
        else:
            cxtEnd=int(startLineNo)+10


        print('meta=========meta====='+metaInfo)
        
        if 'startline' in metaInfo:
            metaInfo = metaInfo.split('startline')[0]
            

            
        if (int(cxtEnd) - int(startLineNo))>10:
            cxtEnd = str(int(startLineNo)+10)
        if (int(startLineNo) - int(cxtStart))>10:
            cxtStart = str(int(startLineNo)-10)       
        cxtStart=str(cxtStart)
        cxtEnd=str(cxtEnd)
        
        print('cxtStart=========cxtStart====='+cxtStart)
        print('cxtEnd=========cxtEnd====='+cxtEnd)

        sample=''
        #get context info
        if cxtStart not in '' and cxtEnd not in '':
            with open(buggy_file_path,'r',encoding='latin1') as perturbFile:
                lines = perturbFile.readlines()
                for i in range(0,len(lines)):
                    if i > int(cxtStart)-2 and i < int(cxtEnd):
                        l = lines[i]
                        l = l.strip()
                        #remove comments
                        if  l.startswith('/') or l.startswith('*'):
                            l = ' '
                        l = l.replace('  ','').replace('\r','').replace('\n','')
                        l = l.split('// ')[0]
                        if int(bno) > 0:
                            if i == int(startLineNo)-1:
                                l=' [BUGGY] '+l
                            elif i == int(startLineNo)+ int(bno) -1:
                                l= ' [BUGGY] '+l
                        elif int(bno) == 0:
                            if i == int(startLineNo)-1:
                                l=' [BUGGY] [BUGGY] '+l
        
                        cxt+=l+' '

        
        sample+='[BUG] [BUGGY] ' + buggyLines +' '+ diagnosticMsg+' '+' [CONTEXT] ' + cxt + ' [CLASS]  '+ metaInfo

        sample = sample.replace('\r','').replace('\n','').replace('\t','')
        sample = sample.replace('  ',' ')
        print(sample)
        
        with open(input_path, 'a') as csvfile:
            filewriter = csv.writer(csvfile, delimiter='\t',  escapechar=' ', 
                                quoting=csv.QUOTE_NONE)
            filewriter.writerow([str(counter), '[PATCH] ' + patchLines,sample,bug_id.replace('-', '_') +'_'+className+'_'+'1'+'_'+'1',startLineNo,str(bno),join(d4j_proj.get_rela_src_path(), file_rela_path)])
            counter += 1