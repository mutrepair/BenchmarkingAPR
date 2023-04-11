import sys
import os
import re
import json
import shutil
import subprocess as sp
from pathlib import Path

save_stdout = sys.stdout
save_stderr = sys.stderr
TESTER_DIR = os.path.abspath(__file__)[: os.path.abspath(__file__).rindex('/') + 1]
DATA_DIR = TESTER_DIR + '../../data/data/'
TESTER_DIR_PATH = Path(TESTER_DIR)
mutResultDirPath = TESTER_DIR_PATH / 'mutResults-d4j'
mutResultDirPath.mkdir(exist_ok=True)
sys.path.append(TESTER_DIR + '../../data/data/')
sys.path.append(TESTER_DIR + '../validation/')
sys.path.append(TESTER_DIR + '../dataloader/')

# load the path of d4j_scripts
sys.path.append(TESTER_DIR + '/../../../../d4j_scripts/')
sys.path.append(TESTER_DIR + '/../../../../d4j_scripts/d4j_ori')

import tokenization

from prepare_testing_data import prepare_cure_input
from prepare_testing_data import clean_testing_bpe
from generator import generate_gpt_conut
from generator import generate_gpt_fconv
from rerank import cure_rerank
from rerank import read_defects4j_meta
from validate_defects4j import get_strings_numbers
from validate_defects4j import insert_fix_defects4j
from patchSampledMutants import recoverPatchesD4j
from patchSampledMutants import doGeneratePatchedJavaFile

from defects4j import *
from d4j_info import *

def getMutIds(projPath: Path):
    res = []
    # The following code includes the mutants not deleting the whole statements
    mutLog = projPath / 'mutants.log'
    assert mutLog.exists()
    with mutLog.open() as log:
        for line in log:
            if '|==> <NO-OP>' in line:
                continue
            else:
                res.append(line.split(':')[0])
    # # The following code includes the mutants able to affect the tests results & not deleting the whole statements
    # killCsv = projPath / 'kill.csv'
    # assert killCsv.exists()
    # mutLog = projPath / 'mutants.log'
    # assert mutLog.exists()
    # with killCsv.open() as csv:
    #     for line in csv:
    #         if ',FAIL' in line or ',EXC' in line:
    #             res.append(line.split(',')[0])
    # with mutLog.open() as log:
    #     for line in log:
    #         if '|==> <NO-OP>' in line:
    #             id = line.split(':')[0]
    #             if id in res:
    #                 res.remove(id)
    return res

def getMutFixedLine(projPath: Path, mutId: str, projSrcPath=None):
    mutLog = projPath / 'mutants.log'
    projSrcRelativePath = sp.check_output("defects4j export -p dir.src.classes", shell=True, universal_newlines=True, cwd=str(projPath), stderr=sp.DEVNULL).strip() if projSrcPath is None else projSrcPath
    shortPath = sp.check_output('find . -name "*.java"', shell=True, universal_newlines=True, cwd=str(projPath / 'mutants' / mutId)).strip()
    assert mutLog.exists()
    with mutLog.open() as log:
        for line in log:
            if line.startswith(mutId + ':'):
                m = re.match(r'.+:(.*?)@.*:(\d+):.+\n', line)
                if (m is None):
                    print("Mutant-{} has no match for '.+:(.*?)@.*:(\d+):.+\n' in line {}".format(mutId, line))
                assert m is not None
                lineNum = int(m[2])
                javaFilePath = projPath / (projSrcRelativePath + '/' + shortPath)
                with javaFilePath.open() as f:
                    cnt = 1
                    for line in f:
                        if cnt == lineNum:
                            return line
                        cnt += 1

def getMutLineNum(projPath: Path, mutId: str):
    mutLog = projPath / 'mutants.log'
    assert mutLog.exists()
    with mutLog.open() as log:
        for line in log:
            if line.startswith(mutId + ':'):
                m = re.match(r'.+:(\d+):.+\n', line)
                if (m is None):
                    print("Mutant-{} has no match for '.+:(\d+):[^:]+' in line {}".format(mutId, line))
                assert m is not None
                return int(m[1])

def getMutSourcePath(projPath: Path, mutId: str):
    mutantDir = projPath / 'mutants' / mutId
    mutantSourcePathStr = sp.check_output("find {} -name '*.java'".format(mutantDir.resolve()), shell=True, text=True)
    return mutantSourcePathStr.strip()

def getMutRelativeSourcePath(projPath: Path, mutId: str):
    mutantDir = projPath / 'mutants' / mutId
    mutantSourcePathStr = sp.check_output("find . -name '*.java'", shell=True, text=True, cwd=str(mutantDir)).strip()
    return mutantSourcePathStr

def getMutReplaceRelativePath(projPath: Path, mutId: str, projSrcRelativePath=None):
    mutantDir = projPath / 'mutants' / mutId
    mutantSourcePathStr = sp.check_output("find . -name '*.java'", shell=True, text=True, cwd=str(mutantDir)).strip()
    if projSrcRelativePath is None:
        projSrcRelativePath = sp.check_output("defects4j export -p dir.src.classes", shell=True, text=True, cwd=str(projPath)).strip()
    return projSrcRelativePath + '/' + mutantSourcePathStr


def prepareMutInput(projPath: Path, projName: str):
    for mutId in getMutIds(projPath):
        print('****** Preparing {} Mutant-{} ******'.format(projName, mutId))
        mutDataDir = mutResultDirPath / (projName + '-mutants') / mutId
        if fileExistsAndNotEmpty(mutDataDir / 'input.txt'):
            continue
        mutDataDir.mkdir(parents=True, exist_ok=True)
        mutDataDir.resolve()
        buggyLineNum = getMutLineNum(projPath, mutId)
        prepare_cure_input(
            buggy_file=getMutSourcePath(projPath, mutId),
            start_line=buggyLineNum, 
            end_line=buggyLineNum+1,
            java_class_path=DATA_DIR + 'java_class.json',
            java_keyword_path=DATA_DIR + 'java_keyword.json',
            tmp_dir='/tmp/',
            output_dir=str(mutDataDir)
        )
        
        # subword-nmt apply-bpe -c ../vocabulary/subword.txt < input.txt > input_bpe.txt
        # subword-nmt apply-bpe -c ../vocabulary/subword.txt < identifier.tokens > identifier_bpe.tokens
        sp.run("subword-nmt apply-bpe -c {} < {} > {}".format(DATA_DIR + "../vocabulary/subword.txt", str(mutDataDir / 'input.txt'), str(mutDataDir / 'input_bpe.txt')), shell=True, check=True)
        sp.run("subword-nmt apply-bpe -c {} < {} > {}".format(DATA_DIR + "../vocabulary/subword.txt", str(mutDataDir / 'identifier.tokens'), str(mutDataDir / 'identifier_bpe.tokens')), shell=True, check=True)

        clean_testing_bpe(
            str(mutDataDir / 'input_bpe.txt'),
            str(mutDataDir / 'identifier_bpe.tokens')
        )

def fileExistsAndNotEmpty(p: Path):
    return p.exists() and p.stat().st_size > 0

def prepareMutInputAllIn(projPath: Path, projName: str, mutIds=None):
    mutDataDir = mutResultDirPath / (projName + '-mutants-allin')
    if (mutDataDir / 'identifier_bpe.tokens').exists():
        return
    (mutDataDir / 'identifier.tokens').unlink(missing_ok=True)
    (mutDataDir / 'identifier.txt').unlink(missing_ok=True)
    (mutDataDir / 'input.txt').unlink(missing_ok=True)
    mutIds = mutIds if mutIds is not None else getMutIds(projPath)
    for mutId in mutIds:
        print('****** Preparing {} Mutant-{} ******'.format(projName, mutId))
        
        if projName == 'math-1f' and (mutId == '12183' or mutId == '12181'):
            print('[INFO] Skipping {} mutant-{}'.format(projName, mutId))
            continue
        mutDataDir.mkdir(parents=True, exist_ok=True)
        mutDataDir.resolve()
        buggyLineNum = getMutLineNum(projPath, mutId)
        prepare_cure_input(
            buggy_file=getMutSourcePath(projPath, mutId),
            start_line=buggyLineNum, 
            end_line=buggyLineNum+1,
            java_class_path=DATA_DIR + 'java_class.json',
            java_keyword_path=DATA_DIR + 'java_keyword.json',
            tmp_dir='/tmp/',
            output_dir=str(mutDataDir)
        )

    # subword-nmt apply-bpe -c ../vocabulary/subword.txt < input.txt > input_bpe.txt
    # subword-nmt apply-bpe -c ../vocabulary/subword.txt < identifier.tokens > identifier_bpe.tokens
    sp.run("subword-nmt apply-bpe -c {} < {} > {}".format(DATA_DIR + "../vocabulary/subword.txt", str(mutDataDir / 'input.txt'), str(mutDataDir / 'input_bpe.txt')), shell=True, check=True)
    sp.run("subword-nmt apply-bpe -c {} < {} > {}".format(DATA_DIR + "../vocabulary/subword.txt", str(mutDataDir / 'identifier.tokens'), str(mutDataDir / 'identifier_bpe.tokens')), shell=True, check=True)

    clean_testing_bpe(
        str(mutDataDir / 'input_bpe.txt'),
        str(mutDataDir / 'identifier_bpe.tokens')
    )

def redirectOutErrToLogsAllin(projName: str):
    tmp = mutResultDirPath / (projName + '-mutants-allin')
    tmp.mkdir(exist_ok=True)
    log = tmp / "output.log"
    sys.stdout = log.open(mode='w')
    sys.stderr = sys.stdout

def recoverOutErr():
    sys.stdout = save_stdout
    sys.stderr = save_stderr

def generatePatchesAllIn(projName: str):
    print('****** Patching {} ******'.format(projName))

    mutDataDir = mutResultDirPath / (projName + '-mutants-allin')
    mutDataDir.resolve()
    vocab_file = TESTER_DIR + '../../data/vocabulary/vocabulary.txt'
    input_file = str(mutDataDir / 'input_bpe.txt')
    identifier_txt_file = str(mutDataDir / 'identifier.txt')
    identifier_token_file = str(mutDataDir / 'identifier_bpe.tokens')
    assert mutDataDir.exists() and os.path.exists(vocab_file) and os.path.exists(input_file) and os.path.exists(identifier_txt_file) and os.path.exists(identifier_token_file)

    beam_size = 100
    try:
        model_file = TESTER_DIR + '../../data/models/gpt_conut_1.pt'
        output_file = str(mutDataDir / 'gpt_conut_1.txt')
        if os.path.exists(output_file):
            os.remove(output_file)
        generate_gpt_conut(vocab_file, model_file, input_file, identifier_txt_file, identifier_token_file, output_file, beam_size)
    except:
        import traceback
        traceback.print_exc()
        print('[ERROR] gpt_conut_1.pt failed to generate patches for all inputs in {}'.format(projName))
    try:
        model_file = TESTER_DIR + '../../data/models/gpt_fconv_1.pt'
        output_file = str(mutDataDir / 'gpt_fconv_1.txt')
        if os.path.exists(output_file):
            os.remove(output_file)
        generate_gpt_fconv(vocab_file, model_file, input_file, identifier_txt_file, identifier_token_file, output_file, beam_size)
    except:
        import traceback
        traceback.print_exc()
        print('[ERROR] gpt_fconv_1.pt failed to generate patches for all inputs in {}'.format(projName))

def generatePatches(projPath: Path, projName: str):
    for mutId in getMutIds(projPath):
        print('****** Patching {} Mutant-{} ******'.format(projName, mutId))
        try:
            mutDataDir = mutResultDirPath / (projName + '-mutants') / mutId
            mutDataDir.resolve()
            vocab_file = TESTER_DIR + '../../data/vocabulary/vocabulary.txt'
            input_file = str(mutDataDir / 'input_bpe.txt')
            identifier_txt_file = str(mutDataDir / 'identifier.txt')
            identifier_token_file = str(mutDataDir / 'identifier_bpe.tokens')
            assert mutDataDir.exists() and os.path.exists(vocab_file) and os.path.exists(input_file) and os.path.exists(identifier_txt_file) and os.path.exists(identifier_token_file)

            beam_size = 100
            # os.environ['CUDA_VISIBLE_DEVICES'] = "0"

            model_file = TESTER_DIR + '../../data/models/gpt_conut_1.pt'
            output_file = str(mutDataDir / 'gpt_conut_1.txt')
            if not os.path.exists(output_file) or os.path.getsize(output_file) == 0:
                generate_gpt_conut(vocab_file, model_file, input_file, identifier_txt_file, identifier_token_file, output_file, beam_size)

            model_file = TESTER_DIR + '../../data/models/gpt_fconv_1.pt'
            output_file = str(mutDataDir / 'gpt_fconv_1.txt')
            if not os.path.exists(output_file) or os.path.getsize(output_file) == 0:
                generate_gpt_fconv(vocab_file, model_file, input_file, identifier_txt_file, identifier_token_file, output_file, beam_size)
        except:
            import traceback
            traceback.print_exc()
            print('[ERROR] Failed to generate patch for {} mutant-{}'.format(projName, mutId))

def combineOutputs(projPath: Path, projName: str):
    outputFile1 = mutResultDirPath / (projName + '-mutants') / 'gpt_conut_1.txt'
    outputFile2 = mutResultDirPath / (projName + '-mutants') / 'gpt_fconv_1.txt'
    outputFile1.unlink(missing_ok=True)
    outputFile2.unlink(missing_ok=True)
    cnt = 0

    with outputFile1.open(mode='a') as o1:
        with outputFile2.open(mode='a') as o2:
            for mutId in getMutIds(projPath):
                print('****** Combining {} Mutant-{} ******'.format(projName, mutId))
                mutDataDir = mutResultDirPath / (projName + '-mutants') / mutId
                mutDataDir.resolve()

                file1 = mutDataDir / 'gpt_conut_1.txt'
                file2 = mutDataDir / 'gpt_fconv_1.txt'

                if not file1.exists() or not file2.exists() or file1.stat().st_size == 0 or file2.stat().st_size == 0:
                    print("Skipping Mutant-{}".format(mutId))
                    continue

                with file1.open() as f1:
                    for line in f1:
                        line = re.sub('(\w)-0', r'\1-' + str(cnt), line, count=1)
                        o1.write(line)
                with file2.open() as f2:
                    for line in f2:
                        line = re.sub('(\w)-0', r'\1-' + str(cnt), line, count=1)
                        o2.write(line)
                cnt += 1

def generateMeta(projPath: Path, projName: str, allin=True, mutIds=None):
    projSrcRelativePath = sp.check_output("defects4j export -p dir.src.classes", shell=True, text=True, cwd=str(projPath)).strip()
    file = mutResultDirPath / (projName + '-mutants') / 'meta.txt'
    if allin:
        file = mutResultDirPath / (projName + '-mutants-allin') / 'meta.txt'
    with file.open('a') as f:
        mutIds = mutIds if mutIds is not None else getMutIds(projPath)
        for mutId in mutIds:
            lineNum = getMutLineNum(projPath, mutId)
            res = 'Mutant\t{}\t{}\t{}\t{}\n'.format(mutId, getMutReplaceRelativePath(projPath, mutId, projSrcRelativePath=projSrcRelativePath), lineNum, lineNum)
            f.write(res)

def rerank(projPath: Path, projName: str, allin=True):
    mutDataDir = mutResultDirPath / (projName + '-mutants')
    if allin:
        mutDataDir = mutResultDirPath / (projName + '-mutants-allin')
    mutDataDir.resolve()
    metaFile = mutResultDirPath / (projName + '-mutants') / 'meta.txt'
    if allin:
        metaFile = mutResultDirPath / (projName + '-mutants-allin') / 'meta.txt'
    metaFile.resolve()
    if not metaFile.exists():
        generateMeta(projPath, projName)
    quixbugs_meta = read_defects4j_meta(str(metaFile))
    hypo_path_list = [str(mutDataDir / 'gpt_conut_1.txt')] + [str(mutDataDir / 'gpt_fconv_1.txt')]
    output_path = str(mutDataDir / 'reranked_patches.json')
    cure_rerank(quixbugs_meta, hypo_path_list, output_path)

def compilePatches(projPath: Path, projName: str):
    tmp_dir = str(projPath) + '/'
    projClassDirPath = sp.check_output("defects4j export -p dir.bin.classes", shell=True, text=True, cwd=str(projPath)).strip()
    mutDataDir = mutResultDirPath / (projName + '-mutants')
    mutDataDir.resolve()
    reranked_result_path = str(mutDataDir / 'reranked_patches.json')

    validMutIds = getMutIds(projPath)

    patchId = 0

    reranked_result = json.load(open(reranked_result_path, 'r'))
    for key in reranked_result:
        
        proj, bug_id, path, start_loc, end_loc = key.split('-')
        if bug_id not in validMutIds:
            continue

        print('===== Mutant-{} ====='.format(bug_id))
        
        # bug_start_time = time.time()
        i = 0
        for tokenized_patch in reranked_result[key]['patches']:
            print('***** Patch-{} *****'.format(i))

            i += 1
            # validate 5 hours for each bug at most
            # if time.time() - bug_start_time > 5 * 3600:
                # break
            # validate 5000 patches for each bug at most
            # if len(validated_result[key]['patches']) >= 5000:
            #     break

            score = tokenized_patch['score']
            tokenized_patch = tokenized_patch['patch']
            print("tokenized_patch: '{}'".format(str(tokenized_patch)))


            strings, numbers = get_strings_numbers(tmp_dir + path, (int(start_loc) + int(end_loc)) // 2)
            strings = [item[0] for item in strings][:5]
            numbers = [item[0] for item in numbers][:5]
            print('strings: ' + str(strings))
            print('numbers: ' + str(numbers))
            # one tokenized patch may be reconstructed to multiple source-code patches
            reconstructed_patches = tokenization.token2statement(tokenized_patch.split(' '), numbers, strings)
            print('reconstructed_patches: ' + str(reconstructed_patches))
            # validate most 5 source-code patches come from the same tokenized patch
            for patch in reconstructed_patches[:5]:
                patch = patch.strip()

                patched_file = insert_fix_defects4j(path, int(start_loc), int(end_loc), patch, tmp_dir)
                sourcePath = projPath / path

                FNULL = open(os.devnull, 'w')
                process = sp.Popen('defects4j compile', shell=True, text=True, stdout=FNULL, stderr=FNULL)
                process.communicate()
                ret_code = process.poll()
                if ret_code == 0:
                    print('Compile Succeeded! PatchId: {}'.format(patchId))
                    relativeSourcePath = getMutRelativeSourcePath(projPath, bug_id)
                    targetPatchSourcePathStr = (projName + '-mutants') + '/patches-pool/{}/{}'.format(patchId, relativeSourcePath)  # xxx.java
                    Path(targetPatchSourcePathStr).parent.mkdir(parents=True, exist_ok=True)
                    shutil.copyfile(sourcePath, targetPatchSourcePathStr)
                    shutil.copyfile(projClassDirPath + '/' + relativeSourcePath[:-5]+".class", targetPatchSourcePathStr[:-5] + ".class")
                    patchId += 1
                elif ret_code != 0:
                    print('Compile Failed!')
                    # relativeSourcePath = getMutRelativeSourcePath(projPath, bug_id)
                    # targetPatchSourcePathStr = (projName + '-mutants') + '/patches-pool/{}/{}'.format(patchId, relativeSourcePath)  # xxx.java
                    # Path(targetPatchSourcePathStr).parent.mkdir(parents=True, exist_ok=True)
                    # shutil.copyfile(sourcePath, targetPatchSourcePathStr)
                    # patchId += 1

                shutil.copyfile(patched_file, patched_file.replace('.bak', ''))

def err(msg: str):
    print(msg)

def genPatchedClasses(projPath: Path, projName: str):
    prepareMutInput(projPath, projName)
    generatePatches(projPath, projName)
    combineOutputs(projPath, projName)
    generateMeta(projPath, projName)
    rerank(projPath, projName)
    compilePatches(projPath, projName)

def genPatchedClassesAllIn(projPath: Path, projName: str):
    prepareMutInputAllIn(projPath, projName)
    generatePatchesAllIn(projName)
    generateMeta(projPath, projName, allin=True)
    rerank(projPath, projName, allin=True)
    # compilePatches(projPath, projName)

######## inferencing for d4j bugs

d4jProjDirPath = Path(TESTER_DIR + '/../../../../d4j_buggy_repo')
d4jScriptsDirPath = Path(TESTER_DIR + '/../../../../d4j_scripts')
d4jObjects = {}

def getD4jSingleLineBugs():
    res = []
    d4jSingleLineBugsFile = d4jScriptsDirPath / 'resources' / 'single_line_bug_list.txt'
    with d4jSingleLineBugsFile.open() as f:  
        for line in f:
           res.append(line.strip())
    return res  

def prepareD4jProj():
    targetBugs = getD4jSingleLineBugs()
    for bugId in targetBugs:
        pid, bid = bugId.split('_')
        proj = Defects4J(pid, bid, 'buggy', str(d4jProjDirPath / bugId))
        d4jObjects[bugId] = proj
        proj.checkout()

def genPatchedClassesAllInD4j():
    prepareMutInputAllIn(projPath, projName)
    generatePatchesAllIn(projName)
    generateMeta(projPath, projName, allin=True)
    rerank(projPath, projName, allin=True)

def getD4jBugLine(pid_bid):
    return get_oracle_patch_line(pid_bid)[0]

def prepareMutInputAllInD4j(mutIds=None):
    mutDataDir = mutResultDirPath.resolve() / ('d4j-allin')
    if (mutDataDir / 'identifier_bpe.tokens').exists():
        return
    (mutDataDir / 'identifier.tokens').unlink(missing_ok=True)
    (mutDataDir / 'identifier.txt').unlink(missing_ok=True)
    (mutDataDir / 'input.txt').unlink(missing_ok=True)
    mutIds = mutIds if mutIds is not None else getD4jSingleLineBugs()
    for mutId in mutIds:  # chart_1
        # if mutId != 'jsoup_62':
        #     continue
        print('****** Preparing Defects4j {} ******'.format(mutId))
        mutDataDir.mkdir(parents=True, exist_ok=True)
        buggyLineNum = getD4jBugLine(mutId)
        pid, bid = mutId.split('_')
        p = Defects4J(pid, bid, 'buggy', str(d4jProjDirPath / mutId))
        p.load_properties()
        prepare_cure_input(
            buggy_file=p.get_buggy_file_path(),
            start_line=buggyLineNum, 
            end_line=buggyLineNum+1,
            java_class_path=DATA_DIR + 'java_class.json',
            java_keyword_path=DATA_DIR + 'java_keyword.json',
            tmp_dir='/tmp/',
            output_dir=str(mutDataDir)
        )

    # subword-nmt apply-bpe -c ../vocabulary/subword.txt < input.txt > input_bpe.txt
    # subword-nmt apply-bpe -c ../vocabulary/subword.txt < identifier.tokens > identifier_bpe.tokens
    sp.run("subword-nmt apply-bpe -c {} < {} > {}".format(DATA_DIR + "../vocabulary/subword.txt", str(mutDataDir / 'input.txt'), str(mutDataDir / 'input_bpe.txt')), shell=True, check=True)
    sp.run("subword-nmt apply-bpe -c {} < {} > {}".format(DATA_DIR + "../vocabulary/subword.txt", str(mutDataDir / 'identifier.tokens'), str(mutDataDir / 'identifier_bpe.tokens')), shell=True, check=True)

    clean_testing_bpe(
        str(mutDataDir / 'input_bpe.txt'),
        str(mutDataDir / 'identifier_bpe.tokens')
    )

def generatePatchesAllInD4j():
    print('****** Patching D4j ******')

    mutDataDir = mutResultDirPath.resolve() / ('d4j-allin')
    vocab_file = TESTER_DIR + '../../data/vocabulary/vocabulary.txt'
    input_file = str(mutDataDir / 'input_bpe.txt')
    identifier_txt_file = str(mutDataDir / 'identifier.txt')
    identifier_token_file = str(mutDataDir / 'identifier_bpe.tokens')
    assert mutDataDir.exists() and os.path.exists(vocab_file) and os.path.exists(input_file) and os.path.exists(identifier_txt_file) and os.path.exists(identifier_token_file)

    beam_size = 100
    try:
        model_file = TESTER_DIR + '../../data/models/gpt_conut_1.pt'
        output_file = str(mutDataDir / 'gpt_conut_1.txt')
        if os.path.exists(output_file):
            os.remove(output_file)
        generate_gpt_conut(vocab_file, model_file, input_file, identifier_txt_file, identifier_token_file, output_file, beam_size)
    except:
        import traceback
        traceback.print_exc()
        print('[ERROR] gpt_conut_1.pt failed to generate patches for all inputs in {}'.format('D4j'))
    try:
        model_file = TESTER_DIR + '../../data/models/gpt_fconv_1.pt'
        output_file = str(mutDataDir / 'gpt_fconv_1.txt')
        if os.path.exists(output_file):
            os.remove(output_file)
        generate_gpt_fconv(vocab_file, model_file, input_file, identifier_txt_file, identifier_token_file, output_file, beam_size)
    except:
        import traceback
        traceback.print_exc()
        print('[ERROR] gpt_fconv_1.pt failed to generate patches for all inputs in {}'.format('D4j'))

def generateMetaD4j(mutIds=None):
    # projSrcRelativePath = sp.check_output("defects4j export -p dir.src.classes", shell=True, text=True, cwd=str(projPath)).strip()
    file = mutResultDirPath / 'd4j-allin' / 'meta.txt'
    with file.open('a') as f:
        mutIds = mutIds if mutIds is not None else getD4jSingleLineBugs()
        for mutId in mutIds:
            pid, bid = mutId.split('_')
            p = Defects4J(pid, bid, 'buggy', str(d4jProjDirPath / mutId))
            p.load_properties()
            lineNum = getD4jBugLine(mutId)
            res = '{}\t{}\t{}\t{}\t{}\n'.format(pid, bid, p.get_buggy_file_path(), lineNum, lineNum)
            f.write(res)

def rerankD4j(allin=True):
    mutDataDir = mutResultDirPath / 'd4j-allin'
    metaFile = mutResultDirPath / 'd4j-allin' / 'meta.txt'
    if not metaFile.exists():
        generateMetaD4j()
    quixbugs_meta = read_defects4j_meta(str(metaFile))
    hypo_path_list = [str(mutDataDir / 'gpt_conut_1.txt')] + [str(mutDataDir / 'gpt_fconv_1.txt')]
    output_path = str(mutDataDir / 'reranked_patches.json')
    cure_rerank(quixbugs_meta, hypo_path_list, output_path)

def collectSourcePatchesD4j():
    patchedSourceOutputDir = Path('cure_patches_d4j').resolve()
    resultDirPath = mutResultDirPath / 'd4j-allin'
        # projName = resultDir.name.split('-')[0]
        # for projPath in d4jProjPaths:
        #     if projName == projPath.stem.split('-')[0]:
        #         projSrcRelativePath = src_rela_dir[projName]
    patchDir = resultDirPath / 'patches'
    assert patchDir.exists()
    for patchFile in patchDir.iterdir():
        projName, bid = patchFile.stem.split('-')
        p = Defects4J(projName, bid, 'buggy', str(d4jProjDirPath / ('{}_{}'.format(projName, bid))))
        p.load_properties()
        lineNum = getD4jBugLine(('{}_{}'.format(projName, bid)))
        buggyFile = p.get_buggy_file_path()
        with patchFile.open() as f:
            patchId = 1
            for patchLine in f:
                print('Processing {}-{}-{}'.format(projName, bid, patchId))
                targetOutputDir = patchedSourceOutputDir / (projName + '_' + bid) / 'patches-pool' / str(patchId)
                doGeneratePatchedJavaFile(Path(buggyFile), lineNum, patchLine, targetOutputDir / (p.get_buggy_class_name().replace('.', '/') + '.java'))
                patchId += 1

def recoverAndCheckCorrectFixesD4j():
    resultDirPath = mutResultDirPath / 'd4j-allin'
    resultJson = resultDirPath / 'reranked_patches.json'
    if resultJson.exists():
        print('='*10 + 'D4J' + '='*10)
        recoverPatchesD4j(resultJson, resultDirPath / 'patches', candidate_size=100, evenly=False)


if __name__ == "__main__":
    os.environ['CUDA_VISIBLE_DEVICES'] = "0"

    # projPath = Path('/home/xxx/check-apr/dataset/chart-1f')
    # projName = 'Chart-1f'
    # genPatchedClassesAllIn(projPath, projName)
    # genPatchedClassesAllInD4j()

    prepareD4jProj()
    prepareMutInputAllInD4j()
    generatePatchesAllInD4j()
    generateMetaD4j()
    rerankD4j()
    recoverAndCheckCorrectFixesD4j()
    collectSourcePatchesD4j()
