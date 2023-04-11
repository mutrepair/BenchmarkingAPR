import codecs
import os
import re
import subprocess

import javalang
from tqdm import tqdm

from CodeAbstract.CA_Recoder import generate_classcontent
from CodeAbstract.CA_SequenceR import run_SequenceR_abs
from CodeAbstract.CA_src2abs import run_src2abs
from Utils.CA_Utils import remove_comments
from Utils.IOHelper import readF2L, writeL2F, readF2L_ori
from Utils._tokenize import CoCoNut_tokenize

"""
ids_f: a list of bug-fix ids
input_dir: raw data dir 
output_dir: where you want to output the processed code of SequenceR
tmp_dir: when building a SequenceR-type context, you need a directory to restore temp files
"""
def preprocess_SequenceR_fromRaw(ids_f,input_dir,output_prefix,tmp_dir):
    ids=readF2L(ids_f)

    def build(src_f, tgt_f, error_f, correct_f, ids):
        buggy_codes = []
        fix_codes = []
        error_ids = []
        correct_ids = []
        ind = 1
        in_count = 0
        for id in ids:
            buginfo = {"_id": id}
            buginfo["buggy_code"] = readF2L_ori(input_dir + "/buggy_methods/" + id + '.txt')
            buginfo["buggy_line"] = codecs.open(input_dir + "/buggy_lines/" + id + '.txt', 'r',
                                                encoding='utf8').read().strip()
            id_metas = codecs.open(input_dir + "/metas/" + id + '.txt', 'r', encoding='utf8').read().strip()
            buginfo["err_start"] = int(str(id_metas.split("<sep>")[2])[1:-1].split(":")[0])
            buginfo["err_end"] = int(str(id_metas.split("<sep>")[2])[1:-1].split(":")[1])

            tmp_f = tmp_dir +'/'+ id + '.txt'
            fix_code = codecs.open(input_dir + '/fix_lines/' + id + '.txt').read().strip()

            buggy_code, hitflag = run_SequenceR_abs(input_dir + "/buggy_classes/" + id + '.txt', tmp_f, buginfo,
                                                    max_length=1000)
            print("hitflag", hitflag)

            if hitflag == 1:
                try:
                    toked_fix = javalang.tokenizer.tokenize(fix_code)
                    toked_fix = ' '.join([tok.value for tok in toked_fix])
                except:
                    toked_fix = re.split(r"([.,!?;(){}])", fix_code)
                    toked_fix = ' '.join(toked_fix)
                try:
                    toked_bug = javalang.tokenizer.tokenize(buggy_code)
                    toked_bug = ' '.join([tok.value for tok in toked_bug]).replace('< START_BUG >',
                                                                                   '<START_BUG>').replace('< END_BUG >',
                                                                                                          '<END_BUG>')
                except:
                    toked_bug = re.split(r"([.,!?;(){}])", buggy_code)
                    toked_bug = ' '.join(toked_bug).replace('< START_BUG >', '<START_BUG>').replace('< END_BUG >',
                                                                                                    '<END_BUG>')
                bug_count = toked_bug.count('<START_BUG>'
                                            )
                if not bug_count == 1:
                    method = buginfo["buggy_code"]
                    err_end=int(buginfo["err_end"])
                    err_start=int(buginfo["err_start"])
                    err_end = min(len(method) - 1, err_end)
                    method[err_end] = "<END_BUG> " + method[err_end].strip()
                    method[err_start] = "<START_BUG> " + method[err_start].strip()
                    method=' '.join(method)
                    try:
                        toked_bug = javalang.tokenizer.tokenize(method)
                        toked_bug = ' '.join([tok.value for tok in toked_bug]).replace('< START_BUG >',
                                                                                       '<START_BUG>').replace(
                            '< END_BUG >', '<END_BUG>')
                    except:
                        toked_bug = re.split(r"([.,!?;(){}])", method)
                        toked_bug = ' '.join(toked_bug).replace('< START_BUG >', '<START_BUG>').replace(
                            '< END_BUG >', '<END_BUG>')
                toked_bug = toked_bug.replace("<START_BUG> <START_BUG>", "<START_BUG>").replace("<END_BUG> <END_BUG>",
                                                                                                "<END_BUG>")
                buggy_codes.append(toked_bug)
                fix_codes.append(toked_fix)

                correct_ids.append(buginfo['_id'])
                in_count += 1
            elif hitflag == 2:
                error_ids.append(buginfo['_id'])
                print(tmp_f)
            else:
                try:
                    toked_fix = javalang.tokenizer.tokenize(fix_code)
                    toked_fix = ' '.join([tok.value for tok in toked_fix])
                except:
                    toked_fix = re.split(r"([.,!?;(){}])", fix_code)
                    toked_fix = ' '.join(toked_fix)
                if True:
                    method = buginfo["buggy_code"]
                    err_end=int(buginfo["err_end"])
                    err_start=int(buginfo["err_start"])
                    err_end=min(len(method)-1,err_end)
                    method[err_end] = "<END_BUG> " + method[err_end]
                    method[err_start] = "<START_BUG> " + method[err_start]
                    method=' '.join(method)
                    try:
                        toked_bug = javalang.tokenizer.tokenize(method)
                        toked_bug = ' '.join([tok.value for tok in toked_bug]).replace('< START_BUG >',
                                                                                       '<START_BUG>').replace(
                            '< END_BUG >', '<END_BUG>')
                    except:
                        toked_bug = re.split(r"([.,!?;(){}])", method)
                        toked_bug = ' '.join(toked_bug).replace('< START_BUG >', '<START_BUG>').replace(
                            '< END_BUG >', '<END_BUG>')
                toked_bug=toked_bug.replace("<START_BUG> <START_BUG>","<START_BUG>").replace("<END_BUG> <END_BUG>","<END_BUG>")
                buggy_codes.append(toked_bug)
                fix_codes.append(toked_fix)

                correct_ids.append(buginfo['_id'])
                in_count += 1
            print(ind, "correct:", len(correct_ids))

            ind += 1
        assert len(buggy_codes) == len(fix_codes)
        # buggy_codes,fix_codes,correct_ids=shuffle(buggy_codes,fix_codes,correct_ids)
        assert len(buggy_codes) == len(fix_codes)
        print(len(buggy_codes), len(fix_codes))

        writeL2F(buggy_codes, src_f)
        writeL2F(fix_codes, tgt_f)
        writeL2F(error_ids, error_f)
        writeL2F(correct_ids, correct_f)
        # build(output_dir+"trn.buggy",output_dir+"trn.fix",output_dir+"trn.fids",output_dir+"trn.sids",ids)
    build(output_prefix+".buggy",output_prefix+".fix",output_prefix+".fids",output_prefix+".sids",ids)
#preprocess_SequenceR_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/bears.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                             #"/home/zhongwenkang/RawData_Processed/SequenceR/bears","/home/zhongwenkang/RawData_Processed/SequenceR/temp")
#preprocess_SequenceR_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/d4j.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                             #"/home/zhongwenkang/RawData_Processed/SequenceR/d4j","/home/zhongwenkang/RawData_Processed/SequenceR/temp")
#preprocess_SequenceR_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/qbs.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                             #"/home/zhongwenkang/RawData_Processed/SequenceR/qbs","/home/zhongwenkang/RawData_Processed/SequenceR/temp")
#preprocess_SequenceR_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/bdj.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                             #"/home/zhongwenkang/RawData_Processed/SequenceR/bdj","/home/zhongwenkang/RawData_Processed/SequenceR/temp")
#preprocess_SequenceR_fromRaw("/home/zhongwenkang/NPR4J_new_test/new_test/test.ids","/home/zhongwenkang/NPR4J_new_test/new_test",
                             #,"/home/zhongwenkang/NPR4J_processed/SequenceR/temp")
"""
ids_f: a list of bug-fix ids
input_dir: raw data dir 
output_dir: where you want to output the processed code of SequenceR
tmp_dir: when building a SequenceR-type context, you need a directory to restore temp files
idiom_path: tokens that will not be abstracted , eg_path: CodeAbstract/CA_Resource/Idioms.2w
mode: when you are preparing test or valid data, using mode 'test'
"""
def preprocess_Tufano_fromRaw(ids_f,input_dir,output_dir,idom_path,temp_dir,mode,max_length=1000):
    ids=readF2L(ids_f)
    buggy_codes = []
    fix_codes = []
    success_ids = []
    fail_ids = []
    ind=0
    for id in ids:
        out_a = temp_dir + "/" + id + "_buggy.txt.abs"
        out_b = temp_dir + "/" + id + "_fix.txt.abs"
        if os.path.exists(out_a) and os.path.exists(out_b):
            print("abstraction file already exists ")
            try:
                buggy_code=codecs.open(out_a,'r',encoding='utf8').read()
                fix_code=codecs.open(out_b,'r',encoding='utf8').read()
                if buggy_code!=fix_code and 1<=len(buggy_code.split())<=max_length :
                    print('added')
                    buggy_codes.append(buggy_code)
                    fix_codes.append(fix_code)
                    success_ids.append(id)
            except:
                fail_ids.append(id)
        else:
            print("generating abstraction")
            buggy_f = input_dir + '/buggy_methods/' + id + ".txt"
            fix_f = input_dir + '/fix_methods/' + id + ".txt"

            if "test" in mode:
                run_src2abs("method",buggy_f,"",out_a,"",idom_path,mode='single')
                run_src2abs("method", fix_f, "", out_b, "", idom_path, mode='single')
            else:
                run_src2abs("method",buggy_f,fix_f,out_a,out_b,idom_path)
            if os.path.exists(out_a) and os.path.exists(out_b):
                try:
                    buggy_code = codecs.open(out_a, 'r', encoding='utf8').read()
                    fix_code = codecs.open(out_b, 'r', encoding='utf8').read()
                    if buggy_code != fix_code and 1<=len(buggy_code.split()) <= max_length:
                        buggy_codes.append(buggy_code)
                        fix_codes.append(fix_code)
                        success_ids.append(id)
                except:
                    fail_ids.append(id)
        print(ind)
        ind+=1

    writeL2F(buggy_codes,output_dir+"/"+mode+".buggy")
    writeL2F(fix_codes, output_dir + "/" + mode + ".fix")
    writeL2F(success_ids, output_dir + "/" + mode + ".sids")
    writeL2F(fail_ids, output_dir + "/" + mode + ".fids")

#peprocess_Tufano_fromRaw(r"/home/zhongwenkang/ML/test/success.ids", "/home/zhongwenkang/ML/test",
#                              "/home/zhongwenkang/ML_Processed/Tufano",
#                              r"/home/zhongwenkang/ML_Processed/Tufano/idioms.txt",
#                              "/home/zhongwenkang/ML_Processed/Tufano/temp", "test")
#preprocess_Tufano_fromRaw(r"/home/zhongwenkang/ML/train/success.ids","/home/zhongwenkang/ML/train","/home/zhongwenkang/ML_Processed/Tufano",
#                          r"/home/zhongwenkang/ML_Processed/Tufano/idioms.txt","/home/zhongwenkang/ML_Processed/Tufano/temp","train")
#preprocess_Tufano_fromRaw(r"/home/zhongwenkang/ML/valid/success.ids","/home/zhongwenkang/ML/valid","/home/zhongwenkang/ML_Processed/Tufano",
#                          r"/home/zhongwenkang/ML_Processed/Tufano/idioms.txt","/home/zhongwenkang/ML_Processed/Tufano/temp","valid")

#preprocess_Tufano_fromRaw("/home/zhongwenkang/NPR4J_new_test/new_test/test.ids","/home/zhongwenkang/NPR4J_new_test/new_test",
                          #"/home/zhongwenkang/NPR4J_processed/Tufano",
                          #"CodeAbstract/CA_Resource/Idioms.2w","/home/zhongwenkang/NPR4J_processed/Tufano/temp/","test")
#preprocess_Tufano_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/bdj.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                          #"/home/zhongwenkang/RawData_Processed/Tufano",
                          #"CodeAbstract/CA_Resource/Idioms.2w","/home/zhongwenkang/RawData_Processed/Tufano/temp/","bdj_test")
#preprocess_Tufano_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/bears.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                          #"/home/zhongwenkang/RawData_Processed/Tufano",
                          #"CodeAbstract/CA_Resource/Idioms.2w","/home/zhongwenkang/RawData_Processed/Tufano/temp/","bears_test")
#preprocess_Tufano_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/d4j.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                          #"/home/zhongwenkang/RawData_Processed/Tufano",
                          #"CodeAbstract/CA_Resource/Idioms.2w","/home/zhongwenkang/RawData_Processed/Tufano/temp/","d4j_test")
#preprocess_Tufano_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/qbs.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                          #"/home/zhongwenkang/RawData_Processed/Tufano",
                          #"CodeAbstract/CA_Resource/Idioms.2w","/home/zhongwenkang/RawData_Processed/Tufano/temp/","qbs_test")
"""
ids_f: a list of bug-fix ids
input_dir: raw data dir 
output_preix: where you want to output the processed code of RewardRepair
tmp_dir: when building a SequenceR-type context, you need a directory to restore temp files
idiom_path: tokens that will not be abstracted , eg_path: CodeAbstract/CA_Resource/Idioms.2w
mode: when you are preparing test or valid data, using mode 'test'
"""
def preprocess_RewardRepair_fromRaw(ids_f,input_dir,output_prefix,tmp_dir,mode="train"):
    ids=readF2L(ids_f)
    bug_fix=[]
    error_ids = []
    correct_ids = []
    if mode == "train":
        bug_fix.append("bugid"+'\t'+"buggy"+'\t'+"patch")
        for idx,id in enumerate(ids):
            buginfo = {"_id": id}
            buginfo["buggy_code"] = readF2L_ori(input_dir + "/buggy_methods/" + id + '.txt')
            buginfo["buggy_line"] = codecs.open(input_dir + "/buggy_lines/" + id + '.txt', 'r',
                                                encoding='utf8').read().strip()
            id_metas = codecs.open(input_dir + "/metas/" + id + '.txt', 'r', encoding='utf8').read().strip()
            buginfo["err_start"] = int(str(id_metas.split("<sep>")[2])[1:-1].split(":")[0])
            buginfo["err_end"] = int(str(id_metas.split("<sep>")[2])[1:-1].split(":")[1])
            tmp_f = tmp_dir +'/'+ id + '.txt'
            fix_code = codecs.open(input_dir + '/fix_lines/' + id + '.txt').read().strip().replace('\t','').replace('\r\n','').replace('\n','')

            buggy_code, hitflag = run_SequenceR_abs(input_dir + "/buggy_classes/" + id + '.txt', tmp_f, buginfo,
                                                    max_length=1000)

            if len(buggy_code.strip()) <= 1:
                hitflag = 0
            print("hitflag",hitflag)
            if hitflag == 1:
                buggy_context=buggy_code.replace("<START_BUG>","").replace("<END_BUG>","").replace('\t','').replace('\r\n','').replace('\n','')
                buggy_line=codecs.open(input_dir + '/buggy_lines/' + id + '.txt').read().strip().replace('\t','').replace('\r\n','').replace('\n','')

                buggy_src="buggy: "+buggy_line+" context: "+buggy_context
                bug_fix.append(buginfo['_id']+'\t'+buggy_src+'\t'+fix_code)
                correct_ids.append(buginfo['_id'])
                print("Total,Success: ",idx, len(correct_ids))
            elif hitflag == 0:
                buggy_method=codecs.open(input_dir + '/buggy_methods/' + id + '.txt').read().strip().replace('\t','').replace('\r\n','').replace('\n','')
                buggy_line = codecs.open(input_dir + '/buggy_lines/' + id + '.txt').read().strip().replace('\t','').replace('\r\n', '').replace('\n', '')
                buggy_src="buggy: "+buggy_line+" context: "+buggy_method
                bug_fix.append(buginfo['_id']+'\t'+buggy_src+'\t'+fix_code)
                correct_ids.append(buginfo['_id'])
                print("Total,Success: ",idx, len(correct_ids))
            elif hitflag == 2:
                error_ids.append(buginfo['_id'])
            else:
                error_ids.append(buginfo['_id'])
    elif mode == "test":
        bug_fix.append("bugid" +'\t'+"store_id"+ '\t' + "buggy" + '\t' + "patch")
        for idx, id in enumerate(ids):
            buginfo = {"_id": id}
            buginfo["buggy_code"] = readF2L_ori(input_dir + "/buggy_methods/" + id + '.txt')
            buginfo["buggy_line"] = codecs.open(input_dir + "/buggy_lines/" + id + '.txt', 'r',
                                                encoding='utf8').read().strip()
            id_metas = codecs.open(input_dir + "/metas/" + id + '.txt', 'r', encoding='utf8').read().strip()
            buginfo["err_start"] = int(str(id_metas.split("<sep>")[2])[1:-1].split(":")[0])
            buginfo["err_end"] = int(str(id_metas.split("<sep>")[2])[1:-1].split(":")[1])
            tmp_f = tmp_dir + '/' + id + '.txt'
            fix_code = codecs.open(input_dir + '/fix_lines/' + id + '.txt').read().strip().replace('\t', '').replace(
                '\r\n', '').replace('\n', '')

            buggy_code, hitflag = run_SequenceR_abs(input_dir + "/buggy_classes/" + id + '.txt', tmp_f, buginfo,
                                                    max_length=1000)

            if len(buggy_code.strip()) <= 1:
                hitflag = 0
            print("hitflag", hitflag)
            if hitflag == 1:
                buggy_context = buggy_code.replace("<START_BUG>", "").replace("<END_BUG>", "").replace('\t',
                                                                                                       '').replace(
                    '\r\n', '').replace('\n', '')
                buggy_line = codecs.open(input_dir + '/buggy_lines/' + id + '.txt').read().strip().replace('\t',
                                                                                                           '').replace(
                    '\r\n', '').replace('\n', '')

                buggy_src = "buggy: " + buggy_line + " context: " + buggy_context
                bug_fix.append(str(idx)+'\t'+buginfo['_id'] + '\t' + buggy_src + '\t' + fix_code)
                correct_ids.append(buginfo['_id'])
                print("Total,Success: ", idx, len(correct_ids))
            elif hitflag == 0:
                buggy_method = codecs.open(input_dir + '/buggy_methods/' + id + '.txt').read().strip().replace('\t',
                                                                                                               '').replace(
                    '\r\n', '').replace('\n', '')
                buggy_line = codecs.open(input_dir + '/buggy_lines/' + id + '.txt').read().strip().replace('\t',
                                                                                                           '').replace(
                    '\r\n', '').replace('\n', '')
                buggy_src = "buggy: " + buggy_line + " context: " + buggy_method
                bug_fix.append(str(idx)+'\t'+buginfo['_id'] + '\t' + buggy_src + '\t' + fix_code)
                correct_ids.append(buginfo['_id'])
                print("Total,Success: ", idx, len(correct_ids))
            elif hitflag == 2:
                error_ids.append(buginfo['_id'])
            else:
                error_ids.append(buginfo['_id'])
    writeL2F(bug_fix, output_prefix + '.bug-fix.csv')
    writeL2F(error_ids, output_prefix + '.fids')
    writeL2F(correct_ids, output_prefix + '.ids')
#preprocess_RewardRepair_fromRaw("/home/zhongwenkang/RawData/Train/trn.ids","/home/zhongwenkang/RawData/Train",
                                #"/home/zhongwenkang/NPR4J_Data/RewardRepair/trn","/home/zhongwenkang/NPR4J_Data/SequenceR/temp_files")
# preprocess_RewardRepair_fromRaw("E:/NPR4J/RawData (2)/Benchmarks/d4j.ids.new","E:/NPR4J/RawData (2)/Benchmarks",
#                                 "D:/RawData_Processed/RewardRepair/d4j","D:/RawData_Processed/RewardRepair/tmp","test")
# preprocess_RewardRepair_fromRaw("E:/NPR4J/RawData (2)/Benchmarks/qbs.ids.new","E:/NPR4J/RawData (2)/Benchmarks",
#                                 "D:/RawData_Processed/RewardRepair/qbs","D:/RawData_Processed/RewardRepair/tmp","test")
# preprocess_RewardRepair_fromRaw("E:/NPR4J/RawData (2)/Benchmarks/bears.ids.new","E:/NPR4J/RawData (2)/Benchmarks",
#                                 "D:/RawData_Processed/RewardRepair/bears","D:/RawData_Processed/RewardRepair/tmp","test")
def preprocess_CodeBertFT_fromRaw(ids_f,input_dir,output_prefix):
    ids=readF2L(ids_f)
    buggy_lines=[]
    fix_lines=[]
    for idx,id in enumerate(ids):
        buggy_line=codecs.open(input_dir+'/buggy_lines/'+id+'.txt','r',encoding='utf8').read().strip()
        fix_line=codecs.open(input_dir+'/fix_lines/'+id+'.txt','r',encoding='utf8').read().strip()
        buggy_lines.append(buggy_line)
        fix_lines.append(fix_line)
    writeL2F(buggy_lines,output_prefix+'.buggy')
    writeL2F(fix_lines,output_prefix+'.fix')
#preprocess_CodeBertFT_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/d4j.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                              #"/home/zhongwenkang/RawData_Processed/CodeBERT-ft/d4j")
#preprocess_CodeBertFT_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/qbs.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                              #"/home/zhongwenkang/RawData_Processed/CodeBERT-ft/qbs")
#preprocess_CodeBertFT_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/bears.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                              #"/home/zhongwenkang/RawData_Processed/CodeBERT-ft/bears")
#preprocess_CodeBertFT_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/bdj.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                              #"/home/zhongwenkang/RawData_Processed/CodeBERT-ft/bdj")
def preprocess_CodeBertFT_fromRaw_methodLevel(ids_f,input_dir,output_prefix):
    ids=readF2L(ids_f)
    print(len(ids))
    buggy_methods=[]
    fix_methods=[]
    for idx,id in enumerate(ids):
        buggy_method=codecs.open(input_dir+'/buggy_methods/'+id+'.txt','r',encoding='utf8').read().strip()
        buggy_method=remove_comments(buggy_method)
        buggy_method=buggy_method.replace('\r\n','').replace('\n','').replace('\t','')
        fix_line=codecs.open(input_dir+'/fix_methods/'+id+'.txt','r',encoding='utf8').read().strip()
        fix_line=remove_comments(fix_line)
        fix_line=fix_line.replace('\r\n','').replace('\n','').replace('\t','')
        buggy_methods.append(buggy_method)
        fix_methods.append(fix_line)
        print(idx)
    writeL2F(buggy_methods,output_prefix+'.buggy')
    writeL2F(fix_methods,output_prefix+'.fix')
#preprocess_CodeBertFT_fromRaw("/home/zhongwenkang/RawData/Valid/valid.ids","/home/zhongwenkang/RawData/Valid",
#                              "/home/zhongwenkang/NPR4J_Data/CodeBertFT/val")
#preprocess_CodeBertFT_fromRaw_methodLevel("/home/zhongwenkang/ML/train/success.ids","/home/zhongwenkang/ML/train",
#                              "/home/zhongwenkang/ML_Processed/CodeBERT/train")
#preprocess_CodeBertFT_fromRaw_methodLevel("/home/zhongwenkang/ML/valid/success.ids","/home/zhongwenkang/ML/valid",
#                              "/home/zhongwenkang/ML_Processed/CodeBERT/valid")

def preprocess_Recoder_fromRaw(mode,ids_f,input_dir,output_dir):
    ids=readF2L(ids_f)
    if mode=="test":
        fail_ids=[]

        for idx,id in enumerate(ids):
            if not os.path.exists(os.path.join(output_dir,id+'.json')):
                print(idx)
                if True:
                    meta_info=codecs.open(os.path.join(input_dir,"metas",id+".txt")).read().strip()
                    filename=meta_info.split("<sep>")[-1].split('@')[0].split("\\")[-1]
                    filename = os.path.abspath(filename)
                    if "BF_Rename" in filename:
                        filename=filename.split('_')[-1].replace('.buggy','.java')
                    class_path=os.path.join(input_dir,"buggy_classes",id+".java")
                    class_path=class_path.replace("d4j_","").replace("bdjar_","").replace("bears_","").replace("qbs_","")
                    generate_classcontent(class_path,os.path.join(output_dir,id+'.json')
                                          ,filename)
                '''
                except:
                    fail_ids.append(id)
                '''
        print(len(fail_ids))
        writeL2F(fail_ids,output_dir+'/fail.ids')


#preprocess_Recoder_fromRaw("test","/home/zhongwenkang/NPR4J_new_test/new_test/test.ids","/home/zhongwenkang/NPR4J_new_test/new_test",
                           #"/home/zhongwenkang/NPR4J_processed/Recoder")
#preprocess_Recoder_fromRaw("test","E:/NPR4J/RawData (2)/Benchmarks/d4j.ids.new",
                           #"E:/NPR4J/RawData (2)/Benchmarks",
                           # "D:/RawData_Processed/Recoder_d4j")
#preprocess_Recoder_fromRaw("test","/home/zhongwenkang/RawData/Evaluation/Benchmarks/bears.ids.new",
                           #"/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                            #"/home/zhongwenkang/RawData_Processed/Recoder")
#preprocess_Recoder_fromRaw("test","/home/zhongwenkang/RawData/Evaluation/Benchmarks/qbs.ids.new",
                           #"/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                            #"/home/zhongwenkang/RawData_Processed/Recoder")
#preprocess_Recoder_fromRaw("test","/home/zhongwenkang/RawData/Evaluation/Benchmarks/bdj.ids.new",
                           #"/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                            #"/home/zhongwenkang/RawData_Processed/Recoder")
"""
ids_f: id list
input_dir: needed buggy_methods buggy_lines fix_lines
temp_prefix: a directory which stores the processed raw data file
output_dir: a directory that stores the compressed data file (fairseq data form)
src_dict_f: dictionary for buggy codes
tgt_dict_f: dictionary for fix codes

before using , cd fairseq, run cmd 'python setup.py develop'
"""
def Preprocess_CoCoNut_fromRaw(ids_f,input_dir,temp_prefix,output_dir,src_dict_f,tgt_dict_f,mode,src_lang="buggy",tgt_lang="fix"):
    ids=readF2L(ids_f)
    buggy_codes=[]
    fix_lines=[]
    for id in ids:
        buggy_method=codecs.open(input_dir+'/buggy_methods/'+id+'.txt').read().strip()
        buggy_line=codecs.open(input_dir+'/buggy_lines/'+id+'.txt').read().strip()
        fix_line=codecs.open(input_dir+'/fix_lines/'+id+'.txt').read().strip()
        buggy_method_toked=CoCoNut_tokenize(buggy_method)
        buggy_line_toked=CoCoNut_tokenize(buggy_line)
        fix_line_toked=CoCoNut_tokenize(fix_line)
        buggy_codes.append(' '.join(buggy_line_toked)+' <CTX> '+' '.join(buggy_method_toked))
        fix_lines.append(' '.join(fix_line_toked))
    assert len(buggy_codes)==len(fix_lines)
    writeL2F(buggy_codes,temp_prefix+'.buggy')
    writeL2F(fix_lines,temp_prefix+'.fix')
    print("Tokenization completed. Now start processing......")
    if "test" in mode:
        cmd = "python fairseq/preprocess.py " + "--CoCoNut-lang " + src_lang + " --target-lang " + tgt_lang + " --workers  1" \
          + " --srcdict " + src_dict_f + " --tgtdict " + tgt_dict_f + " --testpref " + temp_prefix + " --destdir " + output_dir
        print(cmd)
        subprocess.call(cmd, shell=True)
#Preprocess_CoCoNut_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/bdj.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                           #"/home/zhongwenkang/RawData_Processed/CoCoNut/raw_bdj_test","/home/zhongwenkang/RawData_Processed/CoCoNut/bdjar",
                           #"/home/zhongwenkang/RawData_Processed/CoCoNut/dict.ctx.txt","/home/zhongwenkang/RawData_Processed/CoCoNut/dict.fix.txt","bdj_test")
#Preprocess_CoCoNut_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/bears.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                           #,"/home/zhongwenkang/RawData_Processed/CoCoNut/bears",
                           #"/home/zhongwenkang/RawData_Processed/CoCoNut/dict.ctx.txt","/home/zhongwenkang/RawData_Processed/CoCoNut/dict.fix.txt","bears_test")
#Preprocess_CoCoNut_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/qbs.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                           #"/home/zhongwenkang/RawData_Processed/CoCoNut/raw_qbs_test","/home/zhongwenkang/RawData_Processed/CoCoNut/qbs",
                           #"/home/zhongwenkang/RawData_Processed/CoCoNut/dict.ctx.txt","/home/zhongwenkang/RawData_Processed/CoCoNut/dict.fix.txt","qbs_test")
#Preprocess_CoCoNut_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/d4j.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                           #"/home/zhongwenkang/RawData_Processed/CoCoNut/raw_d4j_test","/home/zhongwenkang/RawData_Processed/CoCoNut/d4j",
                           #"/home/zhongwenkang/RawData_Processed/CoCoNut/dict.ctx.txt","/home/zhongwenkang/RawData_Processed/CoCoNut/dict.fix.txt","d4j_test")
#TODO: Preprocess_PatchEdits_fromRawData
def Preprocess_PatchEdits_fromSequenceR(ids_f,SequenceR_buggy_f,SequenceR_fix_f,output_data_f,output_ids_f):
    SequenceR_buggys=readF2L(SequenceR_buggy_f)
    SequenceR_fixes=readF2L(SequenceR_fix_f)
    ids=readF2L(ids_f)
    count=0
    def deal_control_char(s):
        temp = re.sub('[\x00-\x09|\x0b-\x0c|\x0e-\x1f]', '', s)
        return temp
    for i, code in enumerate(tqdm(SequenceR_buggys)):
        if not ("<START_BUG>" in code and "<END_BUG>" in code):
            continue
        fix_code = SequenceR_fixes[i].strip()
        code = deal_control_char(code)
        fix_code = deal_control_char(fix_code)
        while '###' in code:
            code = code.replace('###', '')
        while '###' in fix_code:
            fix_code = fix_code.replace('###', '')
        temp = code
        code = code.strip().split()
        start_index = code.index("<START_BUG>")
        code.remove("<START_BUG>")
        end_index = code.index("<END_BUG>") - 1
        code.remove("<END_BUG>")
        dataset = 'test'
        data = f"{dataset} ### {' '.join(code)} ### {start_index} {end_index} ### <s> {fix_code} </s>\n"
        if data.count('###') != 3:
            print(data.count('###'), '###' in data, temp)
            print(data)
        with open(output_data_f, 'a', encoding='utf8') as fp:
            fp.write(data)

        with open(output_ids_f, 'a', encoding='utf8') as fp:
            fp.write(ids[i]+'\n')
            count += 1
    print(count)
#Preprocess_PatchEdits_fromSequenceR(r"D:\RawData_Processed\SequenceR\qbs.sids",r"D:\RawData_Processed\SequenceR\qbs.buggy",
                                    #r"D:\RawData_Processed\SequenceR\qbs.fix",r"D:\RawData_Processed\PatchEdits\qbs.data",
                                    #r"D:\RawData_Processed\PatchEdits\qbs.ids")
#Preprocess_PatchEdits_fromSequenceR(r"D:\RawData_Processed\SequenceR\bears.sids",r"D:\RawData_Processed\SequenceR\bears.buggy",
                                    #r"D:\RawData_Processed\SequenceR\bears.fix",r"D:\RawData_Processed\PatchEdits\bears.data",
                                    #r"D:\RawData_Processed\PatchEdits\bears.ids")
#Preprocess_PatchEdits_fromSequenceR(r"D:\RawData_Processed\SequenceR\bdj.sids",r"D:\RawData_Processed\SequenceR\bdj.buggy",
                                    #r"D:\RawData_Processed\SequenceR\bdj.fix",r"D:\RawData_Processed\PatchEdits\bdj.data",
                                    #r"D:\RawData_Processed\PatchEdits\bdj.ids")
#Preprocess_PatchEdits_fromSequenceR(r"D:\RawData_Processed\SequenceR\d4j.sids",r"D:\RawData_Processed\SequenceR\d4j.buggy",
                                    #r"D:\RawData_Processed\SequenceR\d4j.fix",r"D:\RawData_Processed\PatchEdits\d4j.data",
                                    #r"D:\RawData_Processed\PatchEdits\d4j.ids")

import sys
if __name__ == '__main__':
    ids_file_name = sys.argv[1]
    ids_info_dir = sys.argv[2]
    processed_dir = sys.argv[3]
    preprocess_Recoder_fromRaw('test', os.path.join(ids_info_dir, ids_file_name), \
    ids_info_dir, processed_dir)