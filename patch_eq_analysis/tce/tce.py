import os, shutil, re, yaml
import subprocess as sp
from pathlib import Path
import filecmp, sys
import multiprocessing
# from synEqResult import *
# from synEqResult_dist import *

eqList = set()
neqList = set()

with open("tce.yaml", 'r') as stream:
    configDict = yaml.safe_load(stream)
    binPatchDict = configDict['binPatch']
for key in binPatchDict:
    binPatchDict[key] = Path(binPatchDict[key]).resolve()

logDirPath = Path(configDict['logDirPath']).resolve()
logDirPath.mkdir(exist_ok=True)

def getD4jProperty(projPath: Path, property: str):
    return sp.check_output("defects4j export -p {}".format(property), shell=True, universal_newlines=True, cwd=str(projPath)).strip()

def getD4jProjBinRelativePath(projPath: Path):
    return getD4jProperty(projPath, 'dir.bin.classes')

def getProjPath(projSimpleName):
    # d4jProjPath = Path('../../d4jProj').resolve()
    d4jProjPath = Path('compiled_repo').resolve()
    for dir in d4jProjPath.iterdir():
        if not dir.is_dir() or not dir.name.endswith('f'):
            continue
        if dir.name.startswith(projSimpleName + '-'):
            return dir

def getSampledMids():
    res = {}
    d4jProjPath = Path('../../d4jProj').resolve()
    for dir in d4jProjPath.iterdir():
        if not dir.is_dir() or not dir.name.endswith('f'):
            continue
        projName = dir.name.split('-')[0]
        res[projName] = []
        sampleTxt = dir / 'sampledMutIds.txt'
        assert sampleTxt.exists()
        with sampleTxt.open() as f:
            for line in f:
                if not line.strip()  == '':
                    res[projName].append(line.strip())
    return res

def printDistResult():

    result = {}

    midList = [x[x.index('-')+1: x.rindex('-')] for x in eqList]
    midList = set(midList)
    d4jProjPath = Path('../../d4jProj').resolve()
    for dir in d4jProjPath.iterdir():
        if not dir.is_dir() or not dir.name.endswith('f'):
            continue
        projName = dir.name.split('-')[0]
        # print(projName)
        logFile = dir / 'mutants.log'
        assert logFile.exists()
        with logFile.open() as f:
            for line in f:
                m = re.match(r'(\d+?):(\w+?):.*\n', line)
                assert m is not None
                midx = m[1]
                mutator = m[2]
                if ('{}-{}'.format(projName, midx) in midList):
                    for id in eqList:
                        apr, pid, mid, patchId = id.split('-')
                        if apr not in result:
                            result[apr] = {}
                        if projName == pid and mid == midx:
                            if mutator not in result[apr]:
                                result[apr][mutator] = []
                            result[apr][mutator].append(id)
    # print(result)

    mutatorList = ['AOR', 'COR', 'LOR', 'LVR', 'ORU', 'ROR', 'SOR']

    for apr in result:
        print(apr)
        for mutator in mutatorList:
            if mutator not in result[apr]:
                print(0)
            else:
                print(len(result[apr][mutator]))



    # result = {}
    # for id in eqList:
    #     apr, pid, mid, patchId = id.split('-')
    #     if apr not in result:
    #         result[apr] = {}


def prepare():
    """
    for each project in ../../d4jProj, backup the build dir and re build all
    """
    d4jBinRelativePath = {}
    d4jProjPath = Path('../../d4jProj').resolve()
    for dir in d4jProjPath.iterdir():
        if not dir.is_dir() or not dir.name.endswith('f'):
            continue
        print('Processing ' + dir.name)
        projName = dir.name.split('-')[0]
        binRelaPath = getD4jProjBinRelativePath(dir)
        d4jBinRelativePath[projName] = binRelaPath
        binPath = dir / binRelaPath
        binBackupPath = dir / (binRelaPath + '.bak')
        if not binBackupPath.exists():
            print('Back up {} to {}'.format(str(binPath), str(binBackupPath)))
            os.rename(str(binPath), str(binBackupPath))
        if binPath.exists():
            shutil.rmtree(str(binPath))
        sp.run('defects4j compile'.split(), cwd=str(dir), check=True)
    print(d4jBinRelativePath)

def isTwoClassSame(patchPath: Path, fixPath: Path, apr, proj, mid, patchId, removeLogs=True):
    tmp = '{}-{}-{}-{}'.format(apr, proj, mid, patchId)
    log1 = logDirPath / '{}-{}-patch.log'.format(tmp, patchPath.name)
    log2 = logDirPath / '{}-{}-fix.log'.format(tmp, fixPath.name)

    if (not log1.exists() and not log2.exists()) or log1.stat().st_size == 0 or log2.stat().st_size == 0:
        sp.run("java -jar asmSynEq.jar {} {} {} {}".format(patchPath, log1, fixPath, log2).split(), check=True, universal_newlines=True)
    assert log1.exists() and log2.exists() and log1.stat().st_size > 0 or log2.stat().st_size > 0

    res = isTwoFileSame(log1, log2)
    if removeLogs:
        os.remove(str(log1))
        os.remove(str(log2))
    return res

def isTwoFileSame(path1: Path, path2: Path):
    return filecmp.cmp(str(path1), str(path2), shallow=False)

def check():
    """
    Check whether the result of directly diff the class file is the same at comparing result of javap.
    """
    # read the log File
    targetLog = Path('bytecodeLevel-recoder.log')
    eqList = []
    neqList = []
    with targetLog.open() as f:
        for line in f:
            id = line.split(':')[1].strip()
            if line.startswith('SynEq:'):
                eqList.append(id)
            else:
                neqList.append(id)
    for apr in binPatchDict:
        binPatchDir = Path(binPatchDict[apr])
        for projDir in binPatchDir.iterdir():
            projName = projDir.name
            projPath = getProjPath(projName)
            projBinDir = projPath / d4jBinRelativePath[projName]
            for midDir in projDir.iterdir():
                for patchIdDir in midDir.iterdir():
                    id = '{}-{}-{}-{}'.format(apr, projName, midDir.name, patchIdDir.name)
                    synEq = None
                    if id in eqList:
                        synEq = True
                    elif id in neqList:
                        synEq = False
                    if synEq is None:
                        continue
                    # find the classes of the patch
                    patches = sp.check_output("find . -name '*.class'", shell=True, cwd=str(patchIdDir), universal_newlines=True).strip().split()
                    # print(str(patchIdDir))
                    # print(patches)
                    allSame = True
                    for patch in patches:
                        patchAbsPath = patchIdDir / patch
                        fixAbsPath = projBinDir / patch
                        if isTwoFileSame(patchAbsPath, fixAbsPath):
                            continue
                        else:
                            allSame=False
                            break
                    if (allSame and synEq) or (not allSame and not synEq):
                        print('Correct: ' + id)
                    else:
                        print('{}: diff class is {}, diff javap result is {}'.format(id, allSame, synEq))

def findBugFixClassFile(projName, mid):
    compiledRepoPath = Path('compiled_repo').resolve()
    src_rela_dir = {"chart": "source", "cli": "src/java", "closure": "src", "codec": "src/java", "collections": "src/main/java", "compress": "src/main/java", "csv": "src/main/java",\
        "gson": "gson/src/main/java", "jacksoncore": "src/main/java", "jacksondatabind": "src/main/java", "jacksonxml": "src/main/java", \
        "jsoup": "src/main/java", "jxpath": "src/java", "lang": "src/main/java", "math": "src/main/java", "mockito": "src", "time": "src/main/java"}
    for projPath in Path(configDict['mutationBenchmarkPath']).iterdir():
        if projPath.is_dir() and projPath.name.endswith('f') and '-' in projPath.name and projName.lower() in projPath.name:
            # projSrcRelativePath = src_rela_dir[projName]
            shortPath = sp.check_output('find . -name "*.java"', shell=True, universal_newlines=True, cwd=str(projPath / 'mutants' / mid)).strip()
            res = compiledRepoPath / (projName + '-1f') / d4jBinRelativePath[projName] / (shortPath.replace('.java', '.class'))
            # print(str(res))
            assert res.exists()
            return res
    print('No fixed class found for {}-{}'.format(projName, mid))

def analyzeAPRWithTask(apr, q, taskList, removeLogs=True):
    for id in taskList:
        aprName, projName, mid, patchId = id.split('-')
        assert apr == aprName
        projPath = getProjPath(projName)
        projBinDir = projPath / d4jBinRelativePath[projName]
        if apr == 'prapr':
            patchFilePath = Path(binPatchDict[apr]) / (projName+'-1f') / mid / 'pool' / 'mutant-{}.class'.format(patchId)
        else:
            patchIdDir = Path(binPatchDict[apr]) / projName / mid / patchId
            if not patchIdDir.exists():
                print('Patch not found: {}: Dir not exists: {}'.format(id, patchIdDir))
                continue
        if id in eqList:
            print('SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
            continue
        elif id in neqList:
            print('Not SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
            continue
        
        if apr == 'prapr':
            patchAbsPath = patchFilePath
            fixAbsPath = findBugFixClassFile(projName, mid)
            if isTwoFileSame(patchAbsPath, fixAbsPath) or isTwoClassSame(patchAbsPath, fixAbsPath, apr, projName, mid, patchId, removeLogs=removeLogs):
                print('SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
            else:
                print('Not SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
        else:
            # find the classes of the patch
            patches = sp.check_output("find . -name '*.class'", shell=True, cwd=str(patchIdDir), universal_newlines=True).strip().split()
            # print(str(patchIdDir))
            # print(patches)
            allSame = True
            for patch in patches:
                patchAbsPath = patchIdDir / patch
                # find the class of the correct fix
                fixAbsPath = projBinDir / patch
                if not patchAbsPath.exists(): 
                    print('[ERROR] {} does not exist!'.format(patchAbsPath))
                    allSame = False
                    break
                elif not fixAbsPath.exists():
                    print('[ERROR] {} does not exist!'.format(fixAbsPath))
                    allSame = False
                    break
                if isTwoFileSame(patchAbsPath, fixAbsPath):
                    # print('Class file identical: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
                    continue
                if isTwoClassSame(patchAbsPath, fixAbsPath, apr, projName, mid, patchId, removeLogs=removeLogs):
                    # print('Same class: {} and {}'.format(fixAbsPath, patchAbsPath))
                    continue
                else:
                    # print('Different class: {} and {}'.format(fixAbsPath, patchAbsPath))
                    allSame=False
                    break
            if allSame:
                print('SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
            else:
                print('Not SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))

def analyzeAPR(apr, q):
    res = []
    binPatchDir = Path(binPatchDict[apr])
    for projDir in binPatchDir.iterdir():
        projName = projDir.name
        projPath = getProjPath(projName)
        projBinDir = projPath / d4jBinRelativePath[projName]
        for midDir in projDir.iterdir():
            for patchIdDir in midDir.iterdir():
                id = '{}-{}-{}-{}'.format(apr, projName, midDir.name, patchIdDir.name)
                if id in eqList:
                    print('SynEq: {}-{}-{}-{}'.format(apr, projName, midDir.name, patchIdDir.name))
                    continue
                elif id in neqList:
                    print('Not SynEq: {}-{}-{}-{}'.format(apr, projName, midDir.name, patchIdDir.name))
                    continue
                # find the classes of the patch
                patches = sp.check_output("find . -name '*.class'", shell=True, cwd=str(patchIdDir), universal_newlines=True).strip().split()
                # print(str(patchIdDir))
                # print(patches)
                allSame = True
                for patch in patches:
                    patchAbsPath = patchIdDir / patch
                    # find the class of the correct fix
                    fixAbsPath = projBinDir / patch
                    if not patchAbsPath.exists(): 
                        print('[ERROR] {} does not exist!'.format(patchAbsPath))
                        allSame = False
                        break
                    elif not fixAbsPath.exists():
                        print('[ERROR] {} does not exist!'.format(fixAbsPath))
                        allSame = False
                        break
                    if isTwoFileSame(patchAbsPath, fixAbsPath):
                        continue
                    elif isTwoClassSame(patchAbsPath, fixAbsPath, apr, projName, midDir.name, patchIdDir.name):
                        # print('Same class: {} and {}'.format(fixAbsPath, patchAbsPath))
                        continue
                    else:
                        # print('Different class: {} and {}'.format(fixAbsPath, patchAbsPath))
                        allSame=False
                        break
                if allSame:
                    print('SynEq: {}-{}-{}-{}'.format(apr, projName, midDir.name, patchIdDir.name))
                    res.append('{}-{}-{}'.format(projName, midDir.name, patchIdDir.name))
                else:
                    print('Not SynEq: {}-{}-{}-{}'.format(apr, projName, midDir.name, patchIdDir.name))
    print('{} finished!'.format(apr))
    print('{}: {}'.format(apr, str(res)))
    if q is None:
        return res
    else:
        q.put((apr, res))

def readResult(logPath):
    logPath = Path(logPath)
    eqList = set()
    neqList = set()
    with logPath.open() as f:
        for line in f:
            if 'SynEq: ' not in line:
                continue
            patchId = line.split(': ')[1].strip()
            if line.startswith('SynEq:'):
                if patchId in neqList:
                    neqList.remove(patchId)
                eqList.add(patchId)
            elif line.startswith('Not SynEq:'):
                if patchId in eqList:
                    eqList.remove(patchId)
                neqList.add(patchId)
    return eqList, neqList

def writeResult(eqList, neqList, outputPath):
    targetPath = Path(outputPath)
    with targetPath.open(mode='w') as f:
        f.write('eqList={}\n'.format(str(eqList)))
        f.write('neqList={}\n'.format(str(neqList)))
    targetPath = Path('synEqResultByTools-dist.py')
    with targetPath.open(mode='w') as f:
        resDict = {}
        for id in eqList:
            apr, projName, mid, patchId = id.split('-')
            if apr not in resDict:
                resDict[apr] = []
            if projName not in resDict[apr]:
                resDict[apr].append('{}-{}-{}'.format(projName, mid, patchId))
        sortedNames = [x for x in d4jBinRelativePath]
        sortedNames.sort()
        for apr in resDict:
            f.write('{}SynEqList={}\n'.format(apr, resDict[apr]))

def startAnalysis():
    syntaxEqDict = {}
    try:
        for apr in binPatchDict:
            syntaxEqDict[apr] = analyzeAPR(apr, None)
    finally:
        for apr in syntaxEqDict:
            print(apr)
            print(syntaxEqDict[apr])
            print(len(syntaxEqDict[apr]))


def startAllAprWithMultiprocessing(processNum):
    q = multiprocessing.Queue()
    for apr in binPatchDict:
        processPool = []
        taskList = []
        binPatchDir = Path(binPatchDict[apr])
        if apr == 'prapr':
            for projDir in binPatchDir.iterdir():
                projName = projDir.name.split('-')[0]
                for midDir in projDir.iterdir():
                    for patchFile in (midDir / 'pool').iterdir():
                        if patchFile.name.endswith('.class') and patchFile.name.startswith('mutant-'):
                            patchId = patchFile.stem.split('-')[1]
                            id = '{}-{}-{}-{}'.format(apr, projName, midDir.name, patchId)
                            if id not in eqList and id not in neqList:
                                taskList.append(id)
        else:
            for projDir in binPatchDir.iterdir():
                projName = projDir.name
                for midDir in projDir.iterdir():
                    for patchIdDir in midDir.iterdir():
                        id = '{}-{}-{}-{}'.format(apr, projName, midDir.name, patchIdDir.name)
                        if id not in eqList and id not in neqList:
                            taskList.append(id)
        size = len(taskList) // processNum
        # print('size: ' + str(size))
        subtaskList = []
        for i in range(processNum):
            if i == processNum - 1:
                subtaskList.append(taskList[i*size:])
            else:
                subtaskList.append(taskList[i*size:(i+1)*size])
        
        assert len(subtaskList) == processNum
        assert sum([len(x) for x in subtaskList]) == len(taskList)

        for subtask in subtaskList:
            p = multiprocessing.Process(target = analyzeAPRWithTask,args=(apr, q, subtask))
            p.start()
            processPool.append(p)
        for p in processPool:
            p.join()

def startOneAprWithMultiprocessing(apr, processNum):
    try:
        processPool = []
        taskList = []
        binPatchDir = Path(binPatchDict[apr])
        if apr == 'prapr':
            for projDir in binPatchDir.iterdir():
                projName = projDir.name.split('-')[0]
                for midDir in projDir.iterdir():
                    mid = midDir.name
                    for clazz in (midDir / 'pool').iterdir():
                        patchId = clazz.stem.split('-')[1]
                        id = '{}-{}-{}-{}'.format(apr, projName, mid, patchId)
                        taskList.append(id)
        else:
            for projDir in binPatchDir.iterdir():
                projName = projDir.name
                for midDir in projDir.iterdir():
                    for patchIdDir in midDir.iterdir():
                        id = '{}-{}-{}-{}'.format(apr, projName, midDir.name, patchIdDir.name)
                        if id not in eqList and id not in neqList:
                            taskList.append(id)
        size = len(taskList) // processNum
        # print('size: ' + str(size))
        subtaskList = []
        for i in range(processNum):
            if i == processNum - 1:
                subtaskList.append(taskList[i*size:])
            else:
                subtaskList.append(taskList[i*size:(i+1)*size])
        
        assert len(subtaskList) == processNum

        q = multiprocessing.Queue()
        for subtask in subtaskList:
            p = multiprocessing.Process(target = analyzeAPRWithTask,args=(apr, q, subtask))
            p.start()
            processPool.append(p)
        for p in processPool:
            p.join()
        # while q.empty() is False:
        #     (aprName, synEqList) = q.get()
        #     print('{}SynEqPatches1700={}'.format(aprName, str(synEqList)))
    finally:
        pass 

def startAnalysisMultiprocessing():
    try:
        q = multiprocessing.Queue()
        for apr in binPatchDict:
            p = multiprocessing.Process(target = analyzeAPR,args=(apr, q))
            p.start()
            processPool.append(p)
        for p in processPool:
            p.join()
        while q.empty() is False:
            (aprName, synEqList) = q.get()
            print('{}SynEqPatches1700={}'.format(aprName, str(synEqList)))
    finally:
        pass
        # eqListx, neqListx = readResult()
        # writeResult(eqListx, neqListx)

d4jBinRelativePath = {'csv': 'target/classes', 'jacksoncore': 'target/classes', 'jacksonxml': 'target/classes', 'lang': 'target/classes', 'cli': 'target/classes', 'jacksondatabind': 'target/classes', 'compress': 'target/classes', 'jsoup': 'target/classes', 'collections': 'target/classes', 'math': 'target/classes', 'chart': 'build', 'time': 'target/classes', 'codec': 'target/classes', 'gson': 'target/classes', 'jxpath': 'target/classes', 'mockito': 'target/classes', 'closure': 'build/classes'}

def getResult():
    resDict = {}
    for id in eqList:
        apr, projName, mid, patchId = id.split('-')
        if apr not in resDict:
            resDict[apr] = {}
        if projName not in resDict[apr]:
            resDict[apr][projName] = set()
        # resDict[apr][projName].append('{}-{}'.format(mid, patchId))
        resDict[apr][projName].add('{}'.format(mid))
    for apr in resDict:
        for name in d4jBinRelativePath:
            if name not in resDict[apr]:
                resDict[apr][name] = []
    sortedNames = [x for x in d4jBinRelativePath]
    sortedNames.sort()
    for apr in resDict:
        print(apr)
        for projName in sortedNames:
            print(len(resDict[apr][projName]))

def printResult():
    resDict = {}
    for id in eqList:
        apr, projName, mid, patchId = id.split('-')
        if apr not in resDict:
            resDict[apr] = []
        if projName not in resDict[apr]:
            resDict[apr].append('{}-{}-{}'.format(projName, mid, patchId))
    sortedNames = [x for x in d4jBinRelativePath]
    sortedNames.sort()
    for apr in resDict:
        print('{}SynEqList={}'.format(apr, resDict[apr]))

def printHelp():
    print('Usage: python3 {} <command> <option>'.format(sys.argv[0]))
    print('command: checkEquivalence, analyze')
    print('For example: python3 {} checkEquivalence'.format(sys.argv[0]))
    print('For example: python3 {} analyze input.log output.py'.format(sys.argv[0]))
    exit(0)

if __name__ == '__main__':
    if len(sys.argv) == 2:
        if sys.argv[1] == 'checkEquivalence':
            startAllAprWithMultiprocessing(configDict['maxMultiProcess'])
        else:
            printHelp()
    elif len(sys.argv) == 4:
        if sys.argv[1] == 'analyze':
            inputLogFilePath = sys.argv[2]
            outputResultFilePath = sys.argv[3]
            eqList, neqList = readResult(inputLogFilePath)
            writeResult(eqList, neqList, outputResultFilePath)
        else:
            printHelp()
    else:
        printHelp()




    # print(getSampledMids())
    # prepare()
    # startAnalysis()
    # check()
    # startAnalysisMultiprocessing()
    # eqList.clear()
    # neqList.clear()
    # startOneAprWithMultiprocessing('prapr', 25)
    # startAllAprWithMultiprocessing(configDict['maxMultiProcess'])

    # eqList.clear()
    # neqList.clear()
    # analyzeAPRWithTask('rewardRepair', None, ['rewardRepair-mockito-481-1'], removeLogs=False)
    # analyzeAPRWithTask('alphaRepair', None, ['alphaRepair-mockito-600-25', 'alphaRepair-mockito-929-0'], removeLogs=False)
    # analyzeAPRWithTask('cure', None, ['cure-mockito-600-6', 'cure-mockito-766-6', 'cure-mockito-722-8'], removeLogs=False)
    # analyzeAPRWithTask('tbar', None, ['tbar-time-14987-2', 'tbar-mockito-1210-4'], removeLogs=False)
    # analyzeAPRWithTask('coconut', None, ['coconut-csv-542-8'], removeLogs=False)
    # analyzeAPRWithTask('rewardRepair', None, ['rewardRepair-chart-64259-1'], removeLogs=False)
    # analyzeAPRWithTask('alphaRepair', None, ['alphaRepair-jacksonxml-666-3','alphaRepair-compress-260-6','alphaRepair-mockito-444-0','alphaRepair-mockito-559-1','alphaRepair-mockito-766-26','alphaRepair-mockito-481-8','alphaRepair-mockito-1916-0','alphaRepair-mockito-644-0','alphaRepair-mockito-1898-0','alphaRepair-mockito-512-0','alphaRepair-mockito-1805-12'], removeLogs=False)
    # analyzeAPRWithTask('selfapr', None, ['selfapr-time-11439-53'], removeLogs=False)

    # eqList, neqList = readResult('bytecodeSynEq-dist.log')
    # writeResult(eqList, neqList, 'result.py')
    # getResult()
    # printResult()

    # printDistResult()