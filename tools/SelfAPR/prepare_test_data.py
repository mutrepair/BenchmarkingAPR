import sys, os, subprocess, csv
sys.path.append(os.path.abspath(__file__ + '/../../../d4j_scripts'))
from os.path import *
from utils import *
from defects4j import *
from major_report_parser import *
from config_loader import load_config

if __name__ == '__main__':
    mode = sys.argv[1]
    common_config = load_config(join(get_config_dir(), 'common', 'common.yaml'))
    dataset_config = load_config(join(get_config_dir(), 'common', 'dataset.yaml'))
    selfapr_config = load_config(join(get_config_dir(), 'selfapr', 'mutants.yaml'))
    mutant_root_dir = rela_to_abs_path(common_config['mutant_root_dir'])
    
    sampled_id_dir = rela_to_abs_path(dataset_config['sample_1700_list_dir'])
    sample_by_mutator_id_dir = rela_to_abs_path(dataset_config['sample_by_mutator_list_dir'])
    tmp_repo_dir = rela_to_abs_path(selfapr_config['tmp_repo_dir'])
    repo_correct_dir = rela_to_abs_path(common_config['validation_repo_root_dir'])
    
    
    if mode == 'd4j':
        sampled_id_list = extract_sampled_bug_id_list(sampled_id_dir)
        input_path = rela_to_abs_path(selfapr_config['input_csv_path'])
    elif mode == 'd4j_add':
        sampled_id_list = extract_sampled_bug_id_list_for_mutators(sample_by_mutator_id_dir)
        input_path = rela_to_abs_path(selfapr_config['input_add_csv_path'])
    else:
        raise Exception('Unknown mode: ' + mode)
    
    with open(input_path, 'w') as csvfile:
        filewriter = csv.writer(csvfile, delimiter='\t',  escapechar=' ', 
                                quoting=csv.QUOTE_NONE)
        filewriter.writerow(["bugid", "patch", "buggy", "id", "buglineNo", "removeNo", "filepath"])
    
    jar_path = abspath(__file__ + '/../perturbation_model/target/perturbation-0.0.1-SNAPSHOT-jar-with-dependencies.jar')
    
    counter = 0
    for bug_id in sampled_id_list:
        proj, mutant_id = bug_id.split('-')
        proj_repo_dir = join(tmp_repo_dir, proj)
        id = '13' if proj == 'mockito' else '25' if proj == 'collections' else '1'
        d4j_proj = Defects4J(proj, id, 'fixed', proj_repo_dir)
        if isdir(proj_repo_dir):
            d4j_proj.clean()
        else:
            d4j_proj.checkout()
        
        mutants = load_mutants(proj, mutant_root_dir)
        mutant = mutants[mutant_id]
        targetfile = join(proj_repo_dir, get_fst_v_src_rela_dir(proj), mutant['path'])
        # d4j_proj.apply_mutant_file(join(mutants_root_dir, proj + '-1f', 'mutants', mutant_id, mutant['path']), mutant['path'])
        mutant_file_path = join(mutant_root_dir, fix_proj_1f(proj + '-1f'), 'mutants', mutant_id, mutant['path'])
        # if not isfile(mutant_file_path):
        #     mutant_file_path = join(mutant_root_dir, proj + '-1f', 'mutants_add', mutant_id, mutant['path'])
        assert isfile(mutant_file_path), 'mutant file not found: ' + mutant_file_path
        d4j_proj.apply_mutant_file(mutant_file_path, mutant['path'])
        failing_test_method, fail_diag = d4j_proj.test_with_report()
        diagnosticMsg = '[FE] ' + fail_diag + ' ' + failing_test_method
        
        
        # code from SelfAPR/3_prepare_test_data.py
        className = mutant['class_name'].split('.')[-1]
        
        correctfile = join(repo_correct_dir, proj, get_fst_v_src_rela_dir(proj), mutant['path'])
        assert isfile(targetfile) and isfile(correctfile)
        buggy_line, patched_line = get_buggy_and_patched_line(targetfile, correctfile)
        hunk_info = str(mutant['line_no']) + '[buggy]' + buggy_line + '[patch]' \
            + patched_line + '[buggyLineNo]' + str(1)
        startLineNo = hunk_info.split('[buggy]')[0]
        buggyLines = hunk_info.split('[buggy]')[1].split('[patch]')[0].strip().replace('\n', ' ')
        patchLines = hunk_info.split('[patch]')[1].split('[buggyLineNo]')[0].strip().replace('\n', ' ')
        bno = hunk_info.split('[buggyLineNo]')[1] # number of buggy lines
        
        print('targetfile'+targetfile)
        print('startLineNo=========startLineNo====='+startLineNo)
        print('bugId=========bugId====='+bug_id)
        print('buggyLines'+buggyLines)
        cxt=''
        metaInfo='' 
        
        cmd = 'timeout 200 java -jar {} '.format(jar_path) +targetfile+' test-'+startLineNo
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
            with open(targetfile,'r',encoding='latin1') as perturbFile:
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
            filewriter.writerow([str(counter), '[PATCH] ' + patchLines,sample,bug_id.replace('-', '_') +'_'+className+'_'+'1'+'_'+'1',startLineNo,str(bno),join(get_fst_v_src_rela_dir(proj), mutant['path'])])
            counter += 1
            
    os.removedirs(tmp_repo_dir)