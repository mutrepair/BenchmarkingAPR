import os, shutil, glob, sys, yaml
import subprocess as sp
from pathlib import Path
import filecmp
import multiprocessing
# from synEqResult_d4j import *

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
    binPatchDict = configDict['binPatch']
for key in binPatchDict:
    binPatchDict[key] = Path(binPatchDict[key]).resolve()

buildRelativePathDict={'codec-16': 'target/classes', 'closure-104': 'build/classes', 'lang-26': 'target/classes', 'math-75': 'target/classes', 'math-30': 'target/classes', 'mockito-34': 'target/classes', 'closure-52': 'build/classes', 'math-57': 'target/classes', 'jacksondatabind-57': 'target/classes', 'jsoup-41': 'target/classes', 'codec-7': 'target/classes', 'jsoup-43': 'target/classes', 'csv-1': 'target/classes', 'math-2': 'target/classes', 'lang-16': 'target/classes', 'gson-13': 'target/classes', 'jacksondatabind-107': 'target/classes', 'compress-23': 'target/classes', 'jsoup-34': 'target/classes', 'mockito-26': 'target/classes', 'jacksondatabind-97': 'target/classes', 'jacksonxml-5': 'target/classes', 'codec-4': 'target/classes', 'math-11': 'target/classes', 'closure-114': 'build/classes', 'mockito-29': 'target/classes', 'jacksondatabind-71': 'target/classes', 'lang-24': 'target/classes', 'jsoup-57': 'target/classes', 'cli-28': 'target/classes', 'math-32': 'target/classes', 'closure-18': 'build/classes', 'closure-125': 'build/classes', 'closure-130': 'build/classes', 'math-82': 'target/classes', 'cli-40': 'target/classes', 'lang-57': 'target/classes', 'jacksondatabind-46': 'target/classes', 'chart-9': 'build', 'closure-57': 'build/classes', 'mockito-5': 'build/classes/main', 'jsoup-51': 'target/classes', 'jsoup-32': 'target/classes', 'compress-19': 'target/classes', 'closure-73': 'build/classes', 'mockito-24': 'target/classes', 'jsoup-88': 'target/classes', 'jacksoncore-8': 'target/classes', 'time-7': 'target/classes', 'math-69': 'target/classes', 'math-70': 'target/classes', 'gson-15': 'target/classes', 'math-104': 'target/classes', 'jacksoncore-25': 'target/classes', 'math-80': 'target/classes', 'jacksondatabind-16': 'target/classes', 'jsoup-49': 'target/classes', 'codec-17': 'target/classes', 'chart-13': 'build', 'csv-14': 'target/classes', 'cli-25': 'target/classes', 'csv-4': 'target/classes', 'jacksondatabind-70': 'target/classes', 'codec-9': 'target/classes', 'math-85': 'target/classes', 'jsoup-15': 'target/classes', 'time-19': 'build/classes', 'jacksondatabind-82': 'target/classes', 'jacksondatabind-27': 'target/classes', 'cli-8': 'target/classes', 'closure-86': 'build/classes', 'lang-61': 'target/classes', 'chart-8': 'build', 'closure-13': 'build/classes', 'time-4': 'target/classes', 'closure-70': 'build/classes', 'jacksondatabind-34': 'target/classes', 'lang-33': 'target/classes', 'closure-102': 'build/classes', 'jsoup-45': 'target/classes', 'jsoup-64': 'target/classes', 'jacksondatabind-96': 'target/classes', 'lang-59': 'target/classes', 'chart-10': 'build', 'math-27': 'target/classes', 'jsoup-9': 'target/classes', 'cli-11': 'target/classes', 'csv-12': 'target/classes', 'jsoup-25': 'target/classes', 'chart-12': 'build', 'time-16': 'build/classes', 'jsoup-77': 'target/classes', 'codec-18': 'target/classes', 'closure-168': 'build/classes', 'lang-29': 'target/classes', 'closure-65': 'build/classes', 'jacksondatabind-17': 'target/classes', 'jsoup-17': 'target/classes', 'math-5': 'target/classes', 'codec-10': 'target/classes', 'jsoup-61': 'target/classes', 'closure-10': 'build/classes', 'chart-11': 'build', 'closure-113': 'build/classes', 'jacksondatabind-37': 'target/classes', 'mockito-38': 'target/classes', 'chart-20': 'build', 'chart-24': 'build', 'jsoup-62': 'target/classes', 'collections-26': 'target/classes', 'math-58': 'target/classes', 'closure-92': 'build/classes', 'jxpath-10': 'target/classes', 'math-63': 'target/classes', 'math-34': 'target/classes', 'compress-38': 'target/classes', 'codec-2': 'target/classes', 'mockito-8': 'build/classes/main', 'jsoup-37': 'target/classes', 'jsoup-46': 'target/classes', 'jsoup-86': 'target/classes', 'math-59': 'target/classes', 'math-96': 'target/classes', 'csv-11': 'target/classes', 'lang-21': 'target/classes', 'chart-1': 'build', 'math-41': 'target/classes', 'jacksoncore-5': 'target/classes', 'closure-14': 'build/classes', 'closure-123': 'build/classes', 'codec-3': 'target/classes', 'closure-38': 'build/classes', 'math-94': 'target/classes', 'math-105': 'target/classes', 'jsoup-47': 'target/classes', 'closure-62': 'build/classes', 'closure-71': 'build/classes', 'math-33': 'target/classes', 'lang-6': 'target/classes', 'closure-67': 'build/classes'}

projNameList = ['csv', 'jacksoncore', 'jacksonxml', 'lang', 'cli', 'jacksondatabind', 'compress', 'jsoup', 'collections', 'math', 'chart', 'time', 'codec', 'gson', 'jxpath', 'mockito', 'closure']


logDirPath = Path(configDict['logDirPath']).resolve()
logDirPath.mkdir(exist_ok=True)

def getD4jProperty(projPath: Path, property: str):
    return sp.check_output("defects4j export -p {}".format(property), shell=True, universal_newlines=True, cwd=str(projPath)).strip()

def getD4jProjBinRelativePath(projPath: Path):
    return getD4jProperty(projPath, 'dir.bin.classes')

def getProjPath(projSimpleName, mid):
    # d4jProjPath = Path('../../d4jProj').resolve()
    d4jProjPath = d4jFixRepoPath
    for dir in d4jProjPath.iterdir():
        # if not dir.is_dir() or not dir.name.endswith('f'):
        #     continue
        if dir.name == projSimpleName + '_' + mid + 'f':
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
        # with log1.open(mode='w') as l1:
        #     with log2.open(mode='w') as l2:
        #         sp.run('javap -c -v -p {}'.format(patchPath).split(), stdout=l1)
        #         sp.run('javap -c -v -p {}'.format(fixPath).split(), stdout=l2)
    assert log1.exists() and log2.exists() and log1.stat().st_size > 0 or log2.stat().st_size > 0
    res = isTwoFileSame(log1, log2)
    if removeLogs:
        os.remove(str(log1))
        os.remove(str(log2))
    return res

def isTwoFileSame(path1: Path, path2: Path):
    return filecmp.cmp(str(path1), str(path2), shallow=False)


def findBugFixClassFile(projName, mid):
    compiledRepoPath = d4jFixRepoPath
    for projPath in Path(configDict['mutationBenchmarkPath']).resolve().iterdir():
        if projPath.is_dir() and projPath.name.endswith('f') and '-' in projPath.name and projName.lower() in projPath.name:
            # projSrcRelativePath = src_rela_dir[projName]
            shortPath = sp.check_output('find . -name "*.java"', shell=True, universal_newlines=True, cwd=str(projPath / 'mutants' / mid)).strip()
            res = compiledRepoPath / (projName + '-1f') / buildRelativePathDict[projName + '-' + mid] / (shortPath.replace('.java', '.class'))
            # print(str(res))
            assert res.exists()
            return res
    print('No fixed class found for {}-{}'.format(projName, mid))


def analyzeAPRWithTask(apr, q, taskList, removeLogs=True):
    for id in taskList:
        aprName, projName, mid, patchId = id.split('-')
        assert apr == aprName
        projPath = getProjPath(projName, mid)
        d4jProjId = projName + '-' + mid
        buildRelativePath = buildRelativePathDict[d4jProjId]
        if apr == 'prapr':
            patchFilePath = Path(binPatchDict[apr]) / (projName) / mid / 'pool' / 'mutant-{}.class'.format(patchId)
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
            proj = Defects4J(projName, mid, 'fixed', str(projPath))
            proj.load_properties()
            fixAbsPath = projPath / buildRelativePath / (proj.get_buggy_class_name().replace('.','/') + '.class')
            if isTwoFileSame(patchAbsPath, fixAbsPath) or isTwoClassSame(patchAbsPath, fixAbsPath, apr, projName, mid, patchId, removeLogs=removeLogs):
                print('SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
            else:
                print('Not SynEq: {}-{}-{}-{}'.format(apr, projName, mid, patchId))
        else:
            # find the classes of the patch
            # patches = sp.check_output("find . -name '*.class'", shell=True, cwd=str(patchIdDir), universal_newlines=True).strip().split()
            patches = glob.glob("{}/**/*.class".format(patchIdDir), recursive=True)
            # print(str(patchIdDir))
            # print(patches)
            assert len(patches) != 0
            allSame = True
            for patch in patches:
                patchRelativePath = os.path.relpath(patch, str(patchIdDir))
                patchAbsPath = patchIdDir / patch
                # find the class of the correct fix
                fixAbsPath = projPath / buildRelativePath / patchRelativePath
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


def readResult(inputPath):
    logPath = Path(inputPath)
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
    targetPath = Path('synEqResultByTools.py')
    with targetPath.open(mode='w') as f:
        resDict = {}
        for id in eqList:
            apr, projName, mid, patchId = id.split('-')
            if apr not in resDict:
                resDict[apr] = []
            if projName not in resDict[apr]:
                resDict[apr].append('{}-{}-{}'.format(projName, mid, patchId))
        sortedNames = [x for x in projNameList]
        sortedNames.sort()
        for apr in resDict:
            f.write('{}SynEqList={}\n'.format(apr, resDict[apr]))

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
        for name in projNameList:
            if name not in resDict[apr]:
                resDict[apr][name] = []
    sortedNames = [x for x in projNameList]
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
    # sortedNames = [x for x in projNameList]
    # sortedNames.sort()
    targetPath = Path('asmSynEqPatches_d4j.py')
    with targetPath.open(mode='w') as f:
        for apr in resDict:
            f.write('{}D4jASMSynEqList={}\n'.format(apr, resDict[apr]))

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

def checkListOfPatches(patchList):
    for patch in patchList:
        print(patch)
        apr, _, _, _ = patch.split('-')
        analyzeAPRWithTask(apr, None, [patch], removeLogs=False)
        patchLogs = glob.glob('{}/{}-*-patch.log'.format(logDirPath, patch), recursive=True)
        fixLogs = glob.glob('{}/{}-*-fix.log'.format(logDirPath, patch), recursive=True)
        if len(patchLogs) == 0 or len(fixLogs) == 0:
            print('No log generated')
        else:
            fixLog = fixLogs[0]
            patchLog = patchLogs[0]
            sp.run('diff -s {} {}'.format(fixLog, patchLog), shell=True)
            os.remove(patchLog)
            os.remove(fixLog)

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
    # analyzeAPRWithTask('rewardRepair', None, ['rewardRepair-mockito-481-1'], removeLogs=False)

    # eqList, neqList = readResult()
    # writeResult(eqList, neqList)
    # # getResult()
    # printResult()
    # printResultAnalysis(eqList)

    # isTwoClassSame(Path('/home/xxx/ssd/bin_patches-d4j/sequencer_patches-d4j/cli/28/1/org/apache/commons/cli/Parser.class'), 
    #                 Path('/home/xxx/research/mutd4j/d4j_scripts/d4jProj/cli_28f/target/classes/org/apache/commons/cli/Parser.class'), 
    #                 'sequencer', 'cli', '28', '1', removeLogs=False)
    # analyzeAPRWithTask('sequencer', None, ['sequencer-cli-28-1'], removeLogs=False)

    # tmpProblematicList=['alphaRepair-cli-28-16','alphaRepair-cli-28-96','alphaRepair-jacksondatabind-34-41','cure-jacksondatabind-16-78','cure-cli-28-1','cure-jacksondatabind-16-68','cure-jacksondatabind-16-67','coconut-jacksondatabind-16-36','coconut-cli-28-37','coconut-jacksondatabind-16-62','coconut-jacksondatabind-16-22','selfapr-closure-57-8','selfapr-jacksondatabind-16-27','selfapr-gson-15-66','selfapr-gson-15-35','selfapr-jacksondatabind-16-44','selfapr-gson-15-72','recoder-cli-28-22','recoder-math-5-2','recoder-closure-10-2','recoder-math-94-30','recoder-cli-28-7','recoder-jacksondatabind-16-14','rewardRepair-math-94-44','rewardRepair-jacksondatabind-16-43','rewardRepair-jacksondatabind-16-77','rewardRepair-csv-11-5','rewardRepair-cli-28-26','rewardRepair-csv-11-20','rewardRepair-csv-11-19','edits-cli-28-18','sequencer-cli-28-1','sequencer-jacksondatabind-16-4','sequencer-closure-73-66']
    # checkListOfPatches(tmpProblematicList)
