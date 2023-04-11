import re, sys
from pathlib import Path
import subprocess as sp

# load the path of d4j_scripts
d4j_scripts_Path = Path('../../d4j_scripts').resolve()
sys.path.append(str(d4j_scripts_Path))
sys.path.append(str(d4j_scripts_Path / 'd4j_ori'))
from defects4j import *
from d4j_info import *

d4jBuggyProjDirPath = Path('../../d4j_buggy_repo').resolve()

ids_all_info_Path = Path('ids_all_info').resolve()
patchesDirPath = Path('edits-patches-d4j').resolve()

fixedLinesPath = ids_all_info_Path/'fix_lines'
editsDirPath = ids_all_info_Path/'edits'

idsFilePath = editsDirPath / 'mutBench.ids'
predFilePath = editsDirPath / 'mutBench.pred'

d4jProjDirPath = Path('../../MutationBenchmark').resolve()
d4jProjPaths = []
for dir in d4jProjDirPath.iterdir():
    if dir.is_dir():
        d4jProjPaths.append(dir)

def getProjPath(projName: str):
    for path in d4jProjPaths:
        if path.name.startswith(projName):
            return path

def getFixedLine(id:str):
    target = fixedLinesPath / (id + '.txt')
    # print(str(target))
    assert target.exists()
    with target.open() as f:
        return f.read().strip()


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

def getMutFixedFilePath(projName: str, mutId: str, projSrcPath=None):
    for dir in d4jProjDirPath.iterdir():
        if dir.is_dir() and projName in dir.name:
            projPath = dir
            break
    if projSrcPath is None:
        projSrcPath = sp.check_output("defects4j export -p dir.src.classes", shell=True, universal_newlines=True, cwd=str(projPath), stderr=sp.DEVNULL).strip()
    shortPath = sp.check_output('find . -name "*.java"', shell=True, universal_newlines=True, cwd=str(projPath / 'mutants' / mutId)).strip()
    fixedFilePath = Path(projPath / projSrcPath / shortPath)
    assert fixedFilePath.exists()
    return fixedFilePath, shortPath

def f2l(file: Path, keepOrig=False):
    res = []
    with file.open() as f:
        for line in f:
            if keepOrig:
                res.append(line)
            else:
                res.append(line.strip())
    return res

def generatePatchedJavaFile(originalJavaFile, patch: str, range, outputFile: Path):
    origLines = f2l(originalJavaFile, keepOrig=True)
    start, end =range
    newLines = origLines[0:int(start)-1] + [patch] + origLines[int(end)-1:]
    outputFile.parent.mkdir(parents=True, exist_ok=True)
    with outputFile.open(mode='w') as f:
        for line in newLines:
            f.write(line)

src_rela_dir = {"chart": "source", "cli": "src/java", "closure": "src", "codec": "src/java", "collections": "src/main/java", "compress": "src/main/java", "csv": "src/main/java",\
        "gson": "gson/src/main/java", "jacksoncore": "src/main/java", "jacksondatabind": "src/main/java", "jacksonxml": "src/main/java", \
        "jsoup": "src/main/java", "jxpath": "src/java", "lang": "src/main/java", "math": "src/main/java", "mockito": "src", "time": "src/main/java"}

def getD4jBugLine(pid_bid):
    return get_oracle_patch_line(pid_bid)[0]

def recoverPatches(rawDataPath, outputPatchesDirPath, checkCorrect=True, keepPatchedJavaFile=False, d4jBench=False):
    global ids_all_info_Path
    ids_all_info_Path = rawDataPath
    global patchesDirPath
    patchesDirPath = outputPatchesDirPath
    fixedLinesPath = ids_all_info_Path/'fix_lines'
    editsDirPath = ids_all_info_Path/'edits'
    idsFilePath = editsDirPath / 'mutBench.ids'
    predFilePath = editsDirPath / 'mutBench.pred'
    correctPatches = []
    fixedDict = {}
    mutatorDict = {}
    with idsFilePath.open() as f:
        ids = f.read().split()
    i = -1
    with predFilePath.open() as pred:
        fixedLine = None
        projName = None
        projPath = None
        mid = None
        patchId = 0
        for line in pred:
            if line == '\n':
                i += 1
                patchId = 0
                if i < len(ids):
                    id = ids[i]
                    fixedLine = getFixedLine(ids[i])
                    m = re.match(r'(\w+?)(\d+)', id)
                    assert m is not None
                    projName = m[1]
                    projPath = getProjPath(projName)
                    mid = m[2]
                    if projName not in fixedDict:
                        fixedDict[projName] = set()  # or list
                    print('========== {} =========='.format(id))
                else:
                    print('i>=len(ids), might because it is the end of file')
            elif '%: ' in line:
                patch = line.split('%: ')[1]
                patchId += 1
                if checkCorrect:
                    if isExactlySameCode(patch, fixedLine):
                        print('{} is fixed'.format(ids[i]))
                        correctPatches.append('{}-{}-{}'.format(projName, mid, patchId))
                        fixedDict[projName].add(mid)
                        mutator = getMutator(projPath, mid)
                        if mutator not in mutatorDict:
                            mutatorDict[mutator] = []
                        mutatorDict[mutator].append(projName + '-' + mid)
                    patchId += 1
                if keepPatchedJavaFile:
                    # The following code is to generate patched Java file in the Uniapr validation format
                    targetPatchDir = patchesDirPath / '{}_{}'.format(projName, mid) / 'patches-pool' / str(patchId)
                    if d4jBench:
                        bid = mid
                        p = Defects4J(projName, bid, 'buggy', str(d4jBuggyProjDirPath/'{}_{}'.format(projName, bid)))
                        p.load_properties()
                        fixedJavaFile = Path(p.get_buggy_file_path())  # actually is buggy file
                        shortPath = p.get_buggy_class_name().replace('.', '/') + '.java'
                        MutLineNum = getD4jBugLine('{}_{}'.format(projName, bid))
                    else:
                        fixedJavaFile, shortPath = getMutFixedFilePath(projName, mid, src_rela_dir[projName])
                        MutLineNum = getMutLineNum(projPath, mid)
                    targetPatchedJavaFile = targetPatchDir / shortPath
                    generatePatchedJavaFile(fixedJavaFile, patch, (MutLineNum, MutLineNum + 1), targetPatchedJavaFile)
                    print('diff --strip-trailing-cr -u {} {}'.format(str(fixedJavaFile), str(targetPatchedJavaFile)))
                    sp.run('diff --strip-trailing-cr -u {} {}'.format(str(fixedJavaFile), str(targetPatchedJavaFile)), shell=True, universal_newlines=True)
                    # patchId += 1
    if checkCorrect:
        projs = [x for x in fixedDict if x in fixedDict]
        projs.sort()
        for projName in projs:
            print('{}: {}'.format(projName, len(fixedDict[projName])))
        # print(fixedDict)

        print('========== {} =========='.format('Mutator Distribution'))
        mutators = [k for k in mutatorDict]
        mutators.sort()
        for m in mutators:
            print("{}: {}".format(m, mutatorDict[m]))
        for m in mutators:
            print("{}: {}".format(m, len(mutatorDict[m])))

        print('editsCorrectPatches = ' + str(correctPatches))
            
if __name__ == '__main__':
    recoverPatches(checkCorrect=True, keepPatchedJavaFile=True, d4jBench=True)