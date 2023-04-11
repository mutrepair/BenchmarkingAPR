import re, os, sys
import shutil
import subprocess as sp
from pathlib import Path

# load the path of d4j_scripts
d4j_scripts_Path = Path('../../d4j_scripts').resolve()
sys.path.append(str(d4j_scripts_Path))
sys.path.append(str(d4j_scripts_Path / 'd4j_ori'))
from defects4j import *
from d4j_info import *

# ids_all_info_Path = Path('/home/xxx/research/mutBench/npr4j/ids_all_info')
# datasetPath = Path('dataset/mutBench-dist+time2')
# idsFilePath =ids_all_info_Path/'sample_distribution+time2.ids'
# predictionPath = Path('/home/xxx/research/mutBench/tufano/model/50-100/pred-mutBench-dist_100/predictions.beam.mul.txt').resolve()
# patchesDirPath = Path('patches-dist').resolve()


# ids_all_info_Path = Path('/home/xxx/research/mutBench/npr4j/ids_all_info')
# datasetPath = Path('dataset/mutBench-d4j')
# idsFilePath =ids_all_info_Path/'all.ids'
# predictionPath = Path('./model/50-100/pred-mutBench-d4j-new_100/predictions.beam.mul.txt').resolve()
# patchesDirPath = Path('tufano-patches-d4j-new').resolve()

# patchesDirPath.mkdir(exist_ok=True)

# combinedOutputPath = datasetPath/'buggy.txt'
# outputDirPath = (datasetPath/'separate').resolve()
# outputDirPath.mkdir(parents=True, exist_ok=True)
# buggyMethodsDirPath = ids_all_info_Path/'buggy_methods/'

# methodRangeFilePath = ids_all_info_Path/'buggy_methods_range.txt'
# fixJavaFileDirPath = ids_all_info_Path/'fix_classes'
# fixMethodDirPath = ids_all_info_Path/'fix_methods'

d4jBuggyProjDirPath = Path('../../d4j_buggy_repo').resolve()

d4jProjDirPath = Path('../../MutationBenchmark').resolve()

d4jProjPaths = []
for dir in d4jProjDirPath.iterdir():
    if dir.is_dir():
        d4jProjPaths.append(dir)

def getProjPath(projName: str):
    for path in d4jProjPaths:
        if path.name.startswith(projName):
            return path

def f2l(file: Path, keepOrig=False):
    res = []
    with file.open() as f:
        for line in f:
            if keepOrig:
                res.append(line)
            else:
                res.append(line.strip())
    return res

def combineAll():
    with combinedOutputPath.open(mode='w') as f:
        for id in f2l(idsFilePath):
            targetFile = outputDirPath / (id + '.txt')
            assert targetFile.exists()
            with targetFile.open() as t:
                f.write(t.read() + '\n')

def preprocessAll():
    for file in buggyMethodsDirPath.iterdir():
        assert file.name.endswith('.txt')
        print("===== Start {} =====".format(file.name))
        cmd = "java -jar src2abs/target/src2abs-0.1-jar-with-dependencies.jar single method {} {} ./src2abs/idioms/idioms.csv".format(str(file), str(outputDirPath / file.name))
        # print(cmd)
        sp.run(cmd.split(), shell=False, universal_newlines=True, check=True)
        print("===== Finish {} =====".format(file.name))

def loadBuggyMethodRange():
    res = {}
    ranges = f2l(methodRangeFilePath)
    for range in ranges:
        id, start, end = range.strip().split()
        res[id] = (start, end)
    return res

def loadMapFile(path: Path):
    res = {}
    maps = f2l(path)
    i = 0
    if not (maps[13].strip() == "" or maps[13].strip().startswith('STRING')):
        print('[ERROR] {} has problem!'.format(path))
        # return {}
    while i+1 <=len(maps)-1:
        concreteLine = maps[i][:-1]
        absLine = maps[i+1][:-1]
        # if '","' in concreteLine:
        #     concreteLine = concreteLine.replace('","', '"@@@@@"')
        #     conEles = concreteLine.strip().split('@@@@@')
        # elif "','" in concreteLine:
        #     concreteLine = concreteLine.replace("','", '"@@@@@"')
        #     conEles = concreteLine.strip().split('@@@@@')
        # else:
        #     conEles = concreteLine.strip().split(',')
        absEles = absLine.strip().split(',')

        conEles = []
        if len(absEles) != 0:
            if absEles[0].startswith('STRING'):
                if path.name == 'closure14938.txt.map':
                    conEles = ["\n      } else if (makesDicts && !isConstructor) {\n        reportWarning(CONSTRUCTOR_REQUIRED, "]
                elif path.name == 'jacksondatabind6069.txt.map':
                    conEles = ["Unsuitable method (", "Parameter #0 type for factory method (", ") decorated with \n                        +enumClass.getName()+", ") not suitable, must be java.lang.String"]
                elif path.name == 'closure65.txt.map' or path.name == 'closure73.txt.map':
                    conEles = ['"<\\\\"', '"\\\\r"', '"/script"', '"\\\\t"', '"\\\\n"', '"\\\\>"', '"!--"', '"\\\\0"']
                else:
                    insq = False  # in quote
                    lastChar = ''
                    tmp=""
                    for char in concreteLine:
                        if char == '"':
                            tmp += char
                            if insq and lastChar != '\\':
                                insq = False
                            else:
                                insq = True
                            lastChar = char
                        elif char == ",":
                            if insq:
                                tmp += char
                                lastChar = char
                            else:
                                conEles.append(tmp)
                                lastChar = ''
                                continue
                        else:
                            tmp += char
                            lastChar = char
                    if len(tmp) != 0:
                        conEles.append(tmp)
            elif absEles[0].startswith('CHAR'):
                insq = False  # in quote
                tmp=""
                lastChar = ''
                for char in concreteLine:
                    if char == "'":
                        tmp += char
                        if insq and lastChar != '\\':
                            insq = False
                        else:
                            insq = True
                        lastChar = char
                    elif char == ",":
                        if insq:
                            tmp += char
                            lastChar = char
                        else:
                            conEles.append(tmp)
                            lastChar = ''
                            continue
                    else:
                        tmp += char
                        if char == '\\' and lastChar == '\\':
                            lastChar = ''
                        else:
                            lastChar = char
                if len(tmp) != 0:
                    conEles.append(tmp)
            else:
                conEles = concreteLine.strip().split(',')

        if len(conEles) != len(absEles):
            print(str(path))
            print(conEles)
            print(len(conEles))
            print(concreteLine)
            print(str(concreteLine.strip().split(',')))
            print(absEles)
        assert len(conEles) == len(absEles)
        for j in range(len(absEles)):
            res[absEles[j]] = conEles[j]
        i += 2
    return res

def recoverAbsPatch(patchContent: str, mapping: dict):
    keys = [x for x in mapping]
    keys.sort(key=len, reverse=True)
    for key in keys:
        patchContent = patchContent.replace(key, mapping[key])
    return patchContent

def generatePatchedJavaFile(originalJavaFile, patch: str, range, outputFile: Path):
    origLines = f2l(originalJavaFile, keepOrig=True)
    start, end =range
    newLines = origLines[0:int(start)-1] + [patch+'\n'] + origLines[int(end)-1:]
    outputFile.parent.mkdir(parents=True, exist_ok=True)
    with outputFile.open(mode='w') as f:
        for line in newLines:
            f.write(line)

def getMutFixedFilePath(projName: str, mutId: str, projSrcPath=None):
    for dir in d4jProjDirPath.iterdir():
        if dir.is_dir() and projName in dir.name:
            projPath = dir
            break
    projSrcRelativePath = sp.check_output("defects4j export -p dir.src.classes", shell=True, universal_newlines=True, cwd=str(projPath), stderr=sp.DEVNULL).strip() if projSrcPath is None else projSrcPath
    shortPath = sp.check_output('find . -name "*.java"', shell=True, universal_newlines=True, cwd=str(projPath / 'mutants' / mutId)).strip()
    fixedFilePath = Path(projPath / projSrcRelativePath / shortPath)
    assert fixedFilePath.exists()
    return fixedFilePath, shortPath

src_rela_dir = {"chart": "source", "cli": "src/java", "closure": "src", "codec": "src/java", "collections": "src/main/java", "compress": "src/main/java", "csv": "src/main/java",\
        "gson": "gson/src/main/java", "jacksoncore": "src/main/java", "jacksondatabind": "src/main/java", "jacksonxml": "src/main/java", \
        "jsoup": "src/main/java", "jxpath": "src/java", "lang": "src/main/java", "math": "src/main/java", "mockito": "src", "time": "src/main/java"}

def isExactlySameCode(a:str, b:str):
    tmp1 = ''.join(a.split())
    tmp2 = ''.join(b.split())
    return tmp1 == tmp2

def getMutator(projPath: Path, mutId: str):
    mutLog = projPath / 'mutants.log'
    assert mutLog.exists()
    with mutLog.open() as log:
        for line in log:
            if line.startswith(mutId + ':'):
                m = re.match(r'\d+:(\w+):.*\n', line)
                assert m is not None
                return m[1]

def recoverPatchesAll(checkCorrect=True, keepPatchedJavaFile=False, d4jBench=False):
    fixDict = {}
    mutatorDict = {}
    correctPatchIdList = []
    rangeDict = loadBuggyMethodRange()
    predictions = f2l(predictionPath)
    cnt = 0
    for id in f2l(idsFilePath):
        cnt += 1
        m = re.match(r'(\w+?)(\d+)', id)
        assert m is not None
        projName = m[1]
        if projName not in fixDict:
            fixDict[projName] = []
        mid = m[2]
        prediction = predictions[cnt-1]
        abstractPatches = prediction.strip().split('<SEP>')
        mapFile = outputDirPath / (id + '.txt.map')
        assert mapFile.exists()
        mapping = loadMapFile(mapFile)

        fixMethodFile = fixMethodDirPath / (id + '.txt')
        with fixMethodFile.open() as f:
            fixMethodContent = f.read()

        patchId = 1
        for idx, absPatch in enumerate(abstractPatches):
            concretePatch = recoverAbsPatch(absPatch, mapping)
            if checkCorrect and isExactlySameCode(concretePatch, fixMethodContent):
                
                fixDict[projName].append(mid)
                print(id + " is syntactically correctly fixed!")
                
                mutator = getMutator(getProjPath(projName), mid)
                if mutator not in mutatorDict:
                    mutatorDict[mutator] = []
                mutatorDict[mutator].append(projName + '-' + mid)
                
                patchIdentifier = '{}-{}-{}'.format(projName, mid, patchId)
                print('Found syntactically equivalent patch (removing space): ' + patchIdentifier)
                correctPatchIdList.append(patchIdentifier)

            # # The following code is to generate patched Java file and diff files
            # targetPatchedJavaFile = patchesDirPath / ('tufano-{}-{}-{}.java'.format(projName, mid, idx+1))
            # targetDiffFile = patchesDirPath / ('tufano-{}-{}-{}.patch'.format(projName, mid, idx+1))
            # fixedJavaFile, _ = getMutFixedFilePath(projName, mid, src_rela_dir[projName])
            # generatePatchedJavaFile(fixedJavaFile, concretePatch, rangeDict[id], targetPatchedJavaFile)
            # sp.run('diff --strip-trailing-cr -u {} {} > {}'.format(str(fixedJavaFile), str(targetPatchedJavaFile), str(targetDiffFile)), shell=True, universal_newlines=True)
            # os.remove(str(targetPatchedJavaFile))

            if keepPatchedJavaFile:
                # The following code is to generate patched Java file in the Uniapr validation format
                targetPatchDir = patchesDirPath / '{}_{}'.format(projName, mid) / 'patches-pool' / str(patchId)
                if d4jBench:
                    bid = mid
                    p = Defects4J(projName, bid, 'buggy', str(d4jBuggyProjDirPath / '{}_{}'.format(projName, bid)))
                    p.load_properties()
                    fixedJavaFile = Path(p.get_buggy_file_path())  # actually is buggy file
                    shortPath = p.get_buggy_class_name().replace('.', '/') + '.java'
                else:
                    fixedJavaFile, shortPath = getMutFixedFilePath(projName, mid, src_rela_dir[projName])
                targetPatchedJavaFile = targetPatchDir / shortPath
                generatePatchedJavaFile(fixedJavaFile, concretePatch, rangeDict[id], targetPatchedJavaFile)
                print('diff --strip-trailing-cr -u {} {}'.format(str(fixedJavaFile), str(targetPatchedJavaFile)))
                sp.run('diff --strip-trailing-cr -u {} {}'.format(str(fixedJavaFile), str(targetPatchedJavaFile)), shell=True, universal_newlines=True)
            patchId += 1

    if checkCorrect:
        # proj = [k for k in fixDict]
        # proj.sort()
        # for key in proj:
        #     print("{} mutants of {} are correctly (exactly) fixed!".format(len(fixDict[key]), key))
        # print('========== {} =========='.format('Mutator Distribution'))
        # mutators = [k for k in mutatorDict]
        # mutators.sort()
        # for m in mutators:
        #     print("{}: {}".format(m, mutatorDict[m]))
        # for m in mutators:
        #     print("{}: {}".format(m, len(mutatorDict[m])))
        print('tufanoCorrectPatches={}'.format(correctPatchIdList))

if __name__ == '__main__':

    if len(sys.argv) != 2:
        print('Usage: python3 {} <command>'.format(sys.argv[0]))
        print('command: mutationBenchmark, mutatorDistribution, d4j2')
        exit(1)
    command = sys.argv[1]
    if command == 'mutationBenchmark':
        ids_all_info_Path = Path('../npr4j/ids_all_info-mutationBenchmark').resolve()
        datasetPath = Path('dataset/mutationBenchmark').resolve()
        predictionDirPrefix = 'pred-mutationBenchmark_'
        predictionPath = Path('./model/50-100/{}100/predictions.beam.mul.txt'.format(predictionDirPrefix)).resolve()
        patchesDirPath = Path('tufano_patches-mutationBenchmark').resolve()
        patchesDirPath.mkdir(exist_ok=True)

        combinedOutputPath = datasetPath/'buggy.txt'
        outputDirPath = (datasetPath/'separate').resolve()
        outputDirPath.mkdir(parents=True, exist_ok=True)

        idsFilePath =ids_all_info_Path/'all.ids'
        buggyMethodsDirPath = ids_all_info_Path/'buggy_methods/'
        methodRangeFilePath = ids_all_info_Path/'buggy_methods_range.txt'
        fixJavaFileDirPath = ids_all_info_Path/'fix_classes'
        fixMethodDirPath = ids_all_info_Path/'fix_methods'

        preprocessAll()
        combineAll()

        # bash inference.sh <LD_LIBRARY_PATH> <VOCAB_SOURCE> <VOCAB_TARGET> <TEST_SOURCES> <MODEL_DIR> <PRED_DIR_PREFIX>
        command = 'bash inference.sh {} {} {} {} {} {}'.format(
            str(Path('lib64').resolve()), 
            str(Path('./dataset/50-100/train/vocab.buggy.txt').resolve()), 
            str(Path('./dataset/50-100/train/vocab.fixed.txt').resolve()), 
            str(combinedOutputPath), 
            str(Path('./model/50-100').resolve()), 
            predictionDirPrefix)
        sp.run(command.split(), universal_newlines=True, cwd='./seq2seq', check=True)
    
        recoverPatchesAll(checkCorrect=False, keepPatchedJavaFile=True, d4jBench=False)

    elif command == 'mutatorDistribution':
        ids_all_info_Path = Path('../npr4j/ids_all_info-mutatorDistribution').resolve()
        datasetPath = Path('dataset/mutatorDistribution').resolve()
        predictionDirPrefix = 'pred-mutatorDistribution_'
        predictionPath = Path('./model/50-100/{}100/predictions.beam.mul.txt'.format(predictionDirPrefix)).resolve()
        patchesDirPath = Path('tufano_patches-mutatorDistribution').resolve()
        patchesDirPath.mkdir(exist_ok=True)

        combinedOutputPath = datasetPath/'buggy.txt'
        outputDirPath = (datasetPath/'separate').resolve()
        outputDirPath.mkdir(parents=True, exist_ok=True)

        idsFilePath =ids_all_info_Path/'all.ids'
        buggyMethodsDirPath = ids_all_info_Path/'buggy_methods/'
        methodRangeFilePath = ids_all_info_Path/'buggy_methods_range.txt'
        fixJavaFileDirPath = ids_all_info_Path/'fix_classes'
        fixMethodDirPath = ids_all_info_Path/'fix_methods'

        preprocessAll()
        combineAll()

        # bash inference.sh <LD_LIBRARY_PATH> <VOCAB_SOURCE> <VOCAB_TARGET> <TEST_SOURCES> <MODEL_DIR> <PRED_DIR_PREFIX>
        command = 'bash inference.sh {} {} {} {} {} {}'.format(
            str(Path('lib64').resolve()), 
            str(Path('./dataset/50-100/train/vocab.buggy.txt').resolve()), 
            str(Path('./dataset/50-100/train/vocab.fixed.txt').resolve()), 
            str(combinedOutputPath), 
            str(Path('./model/50-100').resolve()), 
            predictionDirPrefix)
        sp.run(command.split(), universal_newlines=True, cwd='./seq2seq', check=True)
    
        recoverPatchesAll(checkCorrect=False, keepPatchedJavaFile=True, d4jBench=False)

    elif command == 'd4j2':
        ids_all_info_Path = Path('../npr4j/ids_all_info-d4j2').resolve()
        datasetPath = Path('dataset/d4j2').resolve()
        predictionDirPrefix = 'pred-d4j2_'
        predictionPath = Path('./model/50-100/{}100/predictions.beam.mul.txt'.format(predictionDirPrefix)).resolve()
        patchesDirPath = Path('tufano_patches-d4j2').resolve()
        patchesDirPath.mkdir(exist_ok=True)

        combinedOutputPath = datasetPath/'buggy.txt'
        outputDirPath = (datasetPath/'separate').resolve()
        outputDirPath.mkdir(parents=True, exist_ok=True)

        idsFilePath =ids_all_info_Path/'all.ids'
        buggyMethodsDirPath = ids_all_info_Path/'buggy_methods/'
        methodRangeFilePath = ids_all_info_Path/'buggy_methods_range.txt'
        fixJavaFileDirPath = ids_all_info_Path/'fix_classes'
        fixMethodDirPath = ids_all_info_Path/'fix_methods'

        preprocessAll()
        combineAll()

        # bash inference.sh <LD_LIBRARY_PATH> <VOCAB_SOURCE> <VOCAB_TARGET> <TEST_SOURCES> <MODEL_DIR> <PRED_DIR_PREFIX>
        command = 'bash inference.sh {} {} {} {} {} {}'.format(
            str(Path('lib64').resolve()), 
            str(Path('./dataset/50-100/train/vocab.buggy.txt').resolve()), 
            str(Path('./dataset/50-100/train/vocab.fixed.txt').resolve()), 
            str(combinedOutputPath), 
            str(Path('./model/50-100').resolve()), 
            predictionDirPrefix)
        sp.run(command.split(), universal_newlines=True, cwd='./seq2seq', check=True)
    
        recoverPatchesAll(checkCorrect=False, keepPatchedJavaFile=True, d4jBench=True)

    # # firstly run preprocessAll(), then run combineAll() to preprocess
    # preprocessAll()
    # combineAll()

    # # To run tufano prediction, run `bash inference.sh` in the seq2seq directory

    # recoverPatchesAll(checkCorrect=True, keepPatchedJavaFile=True, d4jBench=True)