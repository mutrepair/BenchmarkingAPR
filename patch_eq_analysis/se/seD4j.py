import os, shutil, re, glob, sys, yaml
import subprocess as sp
from pathlib import Path
import filecmp
import multiprocessing
# from antlrSynEqResult_d4j import *

# load the path of d4j_scripts
d4j_scripts_Path = Path('../../d4j_scripts').resolve()
sys.path.append(str(d4j_scripts_Path))
sys.path.append(str(d4j_scripts_Path / 'd4j_ori'))
from defects4j import *
from d4j_info import *

d4jFixRepoPath = Path('../../d4j_fix_repo').resolve()

eqList = set()
neqList = set()

with open("tce.yaml", 'r') as stream:
    configDict = yaml.safe_load(stream)
    srcPatchDict = configDict['srcPatch']
for key in srcPatchDict:
    srcPatchDict[key] = Path(srcPatchDict[key]).resolve()

d4jProjDirPath = Path(configDict['mutationBenchmarkPath']).resolve()
d4jProjPaths = []
for dir in d4jProjDirPath.iterdir():
    if dir.is_dir() and dir.name.endswith('f') and '-' in dir.name:
        d4jProjPaths.append(dir)

logDirPath = Path(configDict['logDirPath']).resolve()
logDirPath.mkdir(exist_ok=True)

# def findSingleJavaFile(dirPath: Path, fileName=None):
#     if fileName is None:
#         fileName = '*.java'
#     dirPath = dirPath.resolve()
#     patchFilePath = sp.check_output('find {} -name "{}"'.format(str(dirPath), fileName), shell=True, universal_newlines=True).strip()
#     return Path(patchFilePath)

def findSingleJavaFile(dirPath: Path):
    return Path(glob.glob("{}/**/*.java".format(dirPath), recursive=True)[0])

def findAllSourcePatchTasks(aprName, srcPatchDir):
    res = set()
    # find the source patch file 
    assert srcPatchDir.exists()
    for dir in srcPatchDir.iterdir():
        if not dir.is_dir():
            continue
        if '_' in dir.name:
            projName, mid = dir.name.split('_')
            for patchDir in (dir / 'patches-pool').iterdir():
                if not patchDir.is_dir():
                    continue
                patchId = patchDir.name
                identifier = '{}-{}-{}-{}'.format(aprName, projName, mid, patchId)
                if identifier not in eqList and identifier not in neqList:
                    res.add((identifier, findSingleJavaFile(patchDir)))
                    # print('add ' + identifier)
        elif re.match(r'(\w+?)(\d+)', dir.name):
            # sequencer
            m = re.match(r'(\w+?)(\d+)', dir.name)
            projName = m[1]
            mid = m[2]
            for patchDir in dir.iterdir():
                patchId = patchDir.name
                identifier = '{}-{}-{}-{}'.format(aprName, projName, mid, patchId)
                if identifier not in eqList and identifier not in neqList:
                    res.add((identifier, findSingleJavaFile(patchDir)))
        elif re.match(r'(\w+?)-\d+f', dir.name):
            m = re.match(r'(\w+?)-\d+f', dir.name)
            projName = m[1]
            for mutIdDir in dir.iterdir():
                mid = mutIdDir.name
                if (mutIdDir / 'compilation_failure').exists():
                    for patchFile in (mutIdDir / 'compilation_failure').iterdir():
                        patchId = Path(patchFile).stem
                        identifier = '{}-{}-{}-{}'.format(aprName, projName, mid, patchId)
                        if identifier not in eqList and identifier not in neqList:
                            res.add((identifier, patchFile))
                if (mutIdDir / 'correct').exists():
                    for patchFile in (mutIdDir / 'correct').iterdir():
                        patchId = Path(patchFile).stem
                        identifier = '{}-{}-{}-{}'.format(aprName, projName, mid, patchId)
                        if identifier not in eqList and identifier not in neqList:
                            res.add((identifier, patchFile))
                if (mutIdDir / 'test_failure').exists():
                    for patchFile in (mutIdDir / 'test_failure').iterdir():
                        patchId = Path(patchFile).stem
                        identifier = '{}-{}-{}-{}'.format(aprName, projName, mid, patchId)
                        if identifier not in eqList and identifier not in neqList:
                            res.add((identifier, patchFile))
        else: 
            m = re.match(r'[a-z]+', dir.name)
            assert m is not None
            projName = dir.name
            for mutIdDir in dir.iterdir():
                mid = mutIdDir.name
                for patchFile in mutIdDir.iterdir():
                    patchId = patchFile.stem
                    identifier = '{}-{}-{}-{}'.format(aprName, projName, mid, patchId)
                    if identifier not in eqList and identifier not in neqList:
                        res.add((identifier, patchFile))
    return res

def getD4jProperty(projPath: Path, property: str):
    return sp.check_output("defects4j export -p {}".format(property), shell=True, universal_newlines=True, cwd=str(projPath)).strip()

def getD4jProjBinRelativePath(projPath: Path):
    return getD4jProperty(projPath, 'dir.bin.classes')


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
        sp.run("java -jar antlrSynEq-1.0-SNAPSHOT-shaded.jar {} {} {} {}".format(patchPath, log1, fixPath, log2).split(), check=True, universal_newlines=True)
    assert log1.exists() and log2.exists() and log1.stat().st_size > 0 or log2.stat().st_size > 0

    res = isTwoFileSame(log1, log2)
    if removeLogs:
        os.remove(str(log1))
        os.remove(str(log2))
    return res

def isTwoFileSame(path1: Path, path2: Path):
    res = filecmp.cmp(str(path1), str(path2), shallow=False)
    return res


def getMutDeveloperFixedFile(projName: str, bid: str):
    d4jProjPath = d4jFixRepoPath
    for dir in d4jProjPath.iterdir():
        if dir.name == projName + '_' + bid + 'f':
            proj = Defects4J(projName, bid, 'fixed', str(dir))
            proj.load_properties()
            return Path(proj.get_buggy_file_path())

src_rela_dir = {"chart": "source", "cli": "src/java", "closure": "src", "codec": "src/java", "collections": "src/main/java", "compress": "src/main/java", "csv": "src/main/java",\
        "gson": "gson/src/main/java", "jacksoncore": "src/main/java", "jacksondatabind": "src/main/java", "jacksonxml": "src/main/java", \
        "jsoup": "src/main/java", "jxpath": "src/java", "lang": "src/main/java", "math": "src/main/java", "mockito": "src", "time": "src/main/java"}

def analyzeAPRWithTask(apr, q, taskList, removeLogs=True):
    for id, patchPath in taskList:
        aprName, projName, mid, patchId = id.split('-')
        assert apr == aprName

        if id in eqList:
            print('SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
            continue
        elif id in neqList:
            print('Not SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
            continue
        
        patchAbsPath = patchPath
        fixAbsPath = getMutDeveloperFixedFile(projName, mid)
        if not patchAbsPath.exists(): 
            print('[ERROR] {} does not exist!'.format(patchAbsPath))
            continue
        elif not fixAbsPath.exists():
            print('[ERROR] {} does not exist!'.format(fixAbsPath))
            continue
        if isTwoFileSame(patchAbsPath, fixAbsPath):
            print('SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
        if isTwoClassSame(patchAbsPath, fixAbsPath, apr, projName, mid, patchId, removeLogs=removeLogs):
            print('SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
        else:
            print('Not SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))

def readResult(inputPath):
    logPath = Path(inputPath).resolve()
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
    targetPath = Path(outputPath).resolve()
    with targetPath.open(mode='w') as f:
        f.write('eqList={}\n'.format(str(eqList)))
        f.write('neqList={}\n'.format(str(neqList)))


def startAllAprWithMultiprocessing(processNum):
    q = multiprocessing.Queue()
    for apr in srcPatchDict:
        processPool = []
        taskList = list(findAllSourcePatchTasks(apr, srcPatchDict[apr]))
        size = len(taskList) // processNum
        if size == 0:
            continue
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

def printResult(eqList):
    resDict = {}
    for id in eqList:
        apr, projName, mid, patchId = id.split('-')
        if apr not in resDict:
            resDict[apr] = []
        if '{}-{}-{}'.format(projName, mid, patchId) not in resDict[apr]:
            resDict[apr].append('{}-{}-{}'.format(projName, mid, patchId))
    targetPath = Path('antlrSynEqPatches_d4j.py')
    with targetPath.open(mode='w') as f:
        for apr in resDict:
            f.write('{}AntlrSynEqList={}\n'.format(apr, set(resDict[apr])))

def printResultAnalysis(eqList):
    resDict = {}
    bugsDict = {}
    for id in eqList:
        apr, projName, mid, patchId = id.split('-')
        if apr not in resDict:
            resDict[apr] = []
            bugsDict[apr] = []
        resDict[apr].append('{}-{}-{}'.format(projName, mid, patchId))
        bugsDict[apr].append('{}-{}'.format(projName, mid))
    # sortedNames = [x for x in projNameList]
    # sortedNames.sort()
    for apr in resDict:
        print(apr)
        # Number of synEq patches
        print(len(set(resDict[apr])))
        # Number of synEq bugs
        print(len(set(bugsDict[apr])))

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

    # eqList.clear()
    # neqList.clear()
    # startAllAprWithMultiprocessing(25)

    # eqList.clear()
    # neqList.clear()
    # eqList, neqList = readResult()
    # writeResult(eqList, neqList)
    # printResult(eqList)
    # printResultAnalysis(eqList)

