import os, re, sys, time
import shutil, yaml
import subprocess as sp
from pathlib import Path

with open("validationConfig.yaml", 'r') as stream:
    configDict = yaml.safe_load(stream)
    patchesPathDict = configDict['patches']
for key in patchesPathDict:
    patchesPathDict[key] = Path(patchesPathDict[key]).resolve()

processPool = []  # storing (cmd, cwd, process, logFileObj, dirSlotId, processName, fileToBeReplacedPath)
# if this is set too large, the number of validation time out will increase 
maxMultiProcess = configDict['maxMultiProcess']

dirSlot = [ 0 ] * maxMultiProcess  # 0 means available, 1 means busy

def runCmdAndWaitForFinish(cmd: list, cwd=None):
    process = sp.Popen(cmd, shell=False, stdout=sp.PIPE, stderr=sp.PIPE, cwd=cwd, universal_newlines=True)
    stdout, stderr = process.communicate()
    retCode = process.poll()
    return stdout, stderr, retCode

def checkHaveFailedTest(cwd: str):
    path = Path(cwd) / 'failing_tests'
    if not path.exists():
        return False
    with path.open() as f:
        for line in f:
            if line.startswith('--- '):
                return True
    return False

def waitUntilMultiProcessLessThan(t):
    if len(processPool) >= t:
        print("[INFO] Waiting the number of parallel processes become less than " + str(t))
    while len(processPool) >= t:
        for element in processPool:
            cmd, cwd, process, logFileObj, dirSlotId, processName, targetDirPath = element
            retCode = process.poll()
            # haven't finished yet
            if retCode is None:
                continue
            else:
                print('=' * 10 + ' Process {}: `{}` in "{}" Finished '.format(processName, cmd, cwd) + '=' * 10)
                print('=' * 10 + ' {} retCode: {} '.format(processName, retCode) + '=' * 10)
                projName, mid = processName.split('-')

                processPool.remove(element)
                # dirSlot[dirSlotId] = 0

                # try:
                #     # recover replaced file
                #     shutil.copy(str(fileToBeReplacedPath) + '.bak', str(fileToBeReplacedPath))
                # except:
                #     import traceback
                #     traceback.print_exc()
                #     print('Running "git checkout -- ."')
                #     sp.run('git checkout -- .', shell=True, cwd=cwd)
                
                if logFileObj is not None:
                    logFileObj.close()

                global currentApr

                if retCode == 0:
                    global finishedMids
                    finishedMids.append('{}-{}-{}'.format(currentApr, projName, mid))
                #     plausibleList = getPlausibleListFromValidationLog(Path(logFileObj.name))
                #     validationResultDict[currentApr][projName + '-' + mid] = plausibleList
                # else:
                #     validationResultDict[currentApr][projName + '-' + mid] = 'FAIL'

                if os.path.isdir(targetDirPath):
                    shutil.rmtree(targetDirPath)
                print('Found {} process in pool running'.format(len(processPool)))
                print('Time elpsed: {} s'.format(time.time() - START))
                break

def waitForProcessPoolSlotAvailable():
    waitUntilMultiProcessLessThan(maxMultiProcess)

def waitForProcessPoolFinish():
    waitUntilMultiProcessLessThan(1)

# =============================================================================================

originalD4jProjDirPath = Path(configDict['buggyD4jProjPath']).resolve()

logDirPath = (Path(configDict['logDirPath'])).resolve()
logDirPath.mkdir(exist_ok=True)

validationFinishedMidListDirPath = Path(configDict['finishedDirPath']).resolve()
validationFinishedMidListDirPath.mkdir(exist_ok=True)

d4jMvnProjDir = Path(configDict['d4jMvnProjDir']).resolve()
d4jMvnProjTmpDir = Path(configDict['d4jMvnProjTmpDir']).resolve()


def file2Lines(filePath: Path):
    res = []
    with filePath.open() as f:
        for line in f:
            res.append(line.strip())
    return res

finishedMids = []
targetFinishedMidListFile = validationFinishedMidListDirPath / 'd4jValidation.txt'
if targetFinishedMidListFile.exists():
    finishedMids = file2Lines(targetFinishedMidListFile)
validationResultDict = {}  # apr: {pid-mid: patchIds or FAIL}
currentApr = None
validationResultDirPath = Path(configDict['validationResultDirPath']).resolve()
validationResultDirPath.mkdir(exist_ok=True)

def getFinishedProjPath():
    res = []
    for dir in originalD4jProjDirPath.iterdir():
        if dir.is_dir() and (dir / 'sampledMutIds.txt').exists():
            res.append(dir)
    return res

def getAllProj():
    res = []
    for dir in originalD4jProjDirPath.iterdir():
        if dir.is_dir() and dir.stem.endswith('f'):
            res.append(dir)
    return res

def writeToFile(listOrSet, filePath: Path):
    if not filePath.parent.exists():
        filePath.parent.mkdir(parents=True, exist_ok=True)
    with filePath.open(mode='w') as f:
        for x in listOrSet:
            f.write(str(x) + '\n')

def getProjNameFromProjPath(projPath: Path):
    m = re.match(r'(\w+)-\d+f', projPath.stem)
    assert m is not None
    return m[1]

def getProjFormalNameFromProjSimpleName(name: str):
    formalNameWithCamel = ['JacksonCore', 'JacksonDatabind', 'JacksonXml', 'JxPath']
    for camelName in formalNameWithCamel:
        if name == camelName.lower():
            return camelName
    return name.title()

def getD4jProperty(projPath: Path, property: str):
    return sp.check_output("defects4j export -p {}".format(property), shell=True, universal_newlines=True, cwd=str(projPath)).strip()

def getD4jProjSrcRelativePath(projPath: Path):
    return getD4jProperty(projPath, 'dir.src.classes')

def applyMutant(targetProjPath: Path, originalProjPath: Path, mid: str, srcRelativePath=None):
    mutantsDir = originalProjPath / 'mutants' / mid
    # print(str(mutantsDir))
    assert mutantsDir.exists()
    javaFileRelativePath = sp.check_output('find . -name *.java', shell=True, universal_newlines=True, cwd=str(mutantsDir)).strip()
    mutantPath = mutantsDir / javaFileRelativePath
    # get the patch of the java file to be replaced
    srcRelativePath = srcRelativePath if srcRelativePath is not None else getD4jProjSrcRelativePath(targetProjPath)
    fileToBeReplacedPath = targetProjPath / srcRelativePath / javaFileRelativePath
    shutil.copy(str(fileToBeReplacedPath), str(fileToBeReplacedPath) + '.bak')
    shutil.copy(str(mutantPath), str(fileToBeReplacedPath))
    return str(fileToBeReplacedPath), javaFileRelativePath
    # sp.run("diff -s {} {}".format(str(fileToBeReplacedPath) + '.bak', str(fileToBeReplacedPath)), shell=True, universal_newlines=True)


def tryValidation(validationDirPath:Path, midPatchDir: Path, srcRelativePath:str, buildRelativePath:str, logPath: Path, projName: str, mid: str, projPath: Path):
    if len(processPool) >= maxMultiProcess:
        waitForProcessPoolSlotAvailable()
    print('{0} Start {1}-{2} {0}'.format('=' * 10, projName, mid))
    processName = "{}-{}".format(projName, mid)

    # # select slot
    # assert min(dirSlot) == 0  # there must be at least 1 available slot because of waitForProcessPoolSlotAvailable()
    # for idx, value in enumerate(dirSlot):
    #     if value == 0:
    #         dirSlotId = idx
    #         break

    targetDir = str(validationDirPath)

    print("targetDir: " + targetDir)

    # apply mutant!  # if use d4j test to validate patch then does not need to apply mutant
    # fileToBeReplacedPath, javaFileRelativePath = applyMutant(Path(targetDir), projPath, mid, srcRelativePath=srcRelativePath)
    # sp.run('defects4j compile'.split(), cwd=targetDir, check=True, universal_newlines=True)

    # prepare patches-pool
    patchesPoolPath = os.path.join(targetDir, 'gson', 'patches-pool') if 'gson-' in processName else os.path.join(targetDir, 'patches-pool')
    if os.path.isdir(patchesPoolPath):
        shutil.rmtree(patchesPoolPath)
    shutil.copytree(str(midPatchDir), patchesPoolPath)


    logFilePrefix = str(Path(logPath).parent / 'd4j-{}-{}'.format(projName, mid))

    cmd = 'python3 d4jValidate.py {} {} {}'.format(targetDir, logFilePrefix, buildRelativePath).split()
    cwd = '.'
    # dirSlot[dirSlotId] = 1
    print("Process {}: \"{}\"@\"{}\" has started and been added to the pool, using slot {}.".format(processName, cmd, cwd, "?"))
    process = sp.Popen(cmd, shell=False, cwd=cwd, universal_newlines=True)  # shell=False by default
    processPool.append((cmd, cwd, process, None, "dirSlotId", processName, str(targetDir)))
    return True

def contentInFile(content:str, file:Path):
    with file.open() as f:
        for line in f:
            if content in line:
                return True
    return False

def main():
    allProjs = getAllProj()
    aprs = [x for x in patchesPathDict]
    aprs.sort(reverse=True)
    try:
        for apr in aprs:
            patchesDirPath = patchesPathDict[apr]
            if not patchesDirPath.exists():
                print('[ERROR] {} patch dir does not exists: {}'.format(apr, str(patchesDirPath)))
                continue
            validationResultDict[apr] = {}
            global currentApr
            currentApr = apr
            print('%' * 10 + " Start APR " + apr + ' ' + '%' * 10)
            for projPath in allProjs:  # each projPath correspond to pid-bid
                m = re.match(r'(\w+)_(\d+)f', projPath.stem)
                assert m is not None
                projName = m[1]

                # # ========== TMP ============
                # if projName != 'chart':
                #     continue
                # # ===========================

                bid = m[2]

                patchesDir = patchesDirPath / projName / bid
                if not patchesDir.exists():
                    print("{} does not exist, skipping {}".format(str(patchesDir), projName))
                    continue

                targetValidationDir = d4jMvnProjTmpDir / '{}-{}'.format(projName, bid)
                if not os.path.isdir(str(targetValidationDir)):
                    shutil.copytree(str(projPath), str(targetValidationDir))
                buildRelativePath=getD4jProperty(projPath, 'dir.bin.classes')
                if not (targetValidationDir / buildRelativePath).exists():
                    sp.run('defects4j compile'.split(), cwd=str(targetValidationDir), check=True, universal_newlines=True)

                # midPatchDirs = []
                # for dir in patchesDir.iterdir():
                #     if dir.is_dir():
                #         midPatchDirs.append(dir)

                # for midPatchDir in midPatchDirs:
                #     mid = midPatchDir.stem
                logPath = logDirPath / apr / 'd4j-{}-{}'.format(projName, bid)

                #     for i in range(maxMultiProcess):
                #         slotPath = d4jMvnProjTmpDir / ('{}_{}-{}'.format(projName, mid, i))
                #         if not slotPath.exists():
                #             shutil.copytree(str(d4jMvnProjTmpDir / projName), str(slotPath))
                        
                tryValidation(targetValidationDir, patchesDir, None, buildRelativePath, logPath, projName, bid, projPath)

                
                # deleting the slots dirs
                # for i in range(maxMultiProcess):
                #     slotPath = d4jMvnProjTmpDir / ('{}_{}-{}'.format(projName, mid, i))
                #     if slotPath.exists():
                #         shutil.rmtree(str(slotPath))
                # print('=' * 10 + str(projName) + ' Finished' + '=' * 10)
            waitForProcessPoolFinish()
    finally:
        writeToFile(set(finishedMids), targetFinishedMidListFile)

def getPlausibleListFromValidationLog(logPath: Path):
    assert logPath.exists()
    with logPath.open() as f:
        for line in f:
            if line.startswith('Directory of plausible patches: '):
                tmp = line.strip().split(':')[1].strip()
                return tmp[1:-1].split(', ')

def readResultFileAsDict(targetFile: Path):
    res = {}
    with targetFile.open() as f:
        for line in f:
            tmp = line.strip().split(':')
            key = tmp[0]
            value = tmp[1]
            if ',' in value:
                res[key] = value[-1].split(',')  # the last char is ','
            else:
                res[key] = value
    return res

def dumpValidationResult(outputDir=validationResultDirPath, overwirte=False):
    for apr in validationResultDict:
        if apr not in validationResultDict:
            continue
        dicToWrite = validationResultDict[apr]
        targetFile = outputDir / (apr + '.txt')
        content = ""
        if targetFile.exists():
            dic = readResultFileAsDict(targetFile)
            # update original dictionary
            for key in dicToWrite:
                dic[key] = dicToWrite[key]
            dicToWrite = dic
        for key in dicToWrite:
            value = dicToWrite[key]
            if type(value) is list:
                line = "{}:".format(key)
                for v in value:
                    line += (v + ',')
                line += '\n'
                content += line
            else:
                content += (key + ":" + value + '\n')
        with targetFile.open(mode='w') as f:
            f.write(content)

def listAsString(lst: list, d=','):
    res = ''
    for x in lst:
        res += (str(x) + ',')
    return res[:-1]

def getSampledMids():
    res = {}
    d4jProjPath = Path('../d4jProj').resolve()
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

d4j120Projects = ["chart", "closure", "lang", "math", "mockito", "time"]
d4j200Projects = ["cli", "codec", "collections", "compress", "csv", "gson", "jacksoncore", "jacksondatabind", "jacksonxml", "jsoup", "jxpath"]

def analyzeValidationResultForAll(d4jVersion='all'):
    # midDict = getSampledMids()
    compilableDict = {}
    plausibleDict = {}
    # errorDict = {}
    for techDir in logDirPath.iterdir():
        if not techDir.is_dir():
            continue
        if not techDir.name == 'tufano-d4j':
            continue
        apr = techDir.name.split('-')[0]
        # if 'distribution' not in apr:
        #     continue
        # initialize dicts
        compilableDict[apr] = []
        plausibleDict[apr] = []
        # errorDict[apr] = []
     
        for logFile in techDir.iterdir():
            if logFile.is_file() and logFile.name.endswith('.log'):
                patchId = logFile.stem[logFile.stem.index('-')+1:]
                if d4jVersion == 'all'  \
                        or (d4jVersion == '2.0.0' and patchId.split('-')[0] in d4j200Projects)  \
                        or (d4jVersion == '1.2.0' and patchId.split('-')[0] in d4j120Projects):
                    compilableDict[apr].append(patchId)
                    if contentInFile("Failing tests: 0", logFile):
                        plausibleDict[apr].append(patchId)

    aprs = [x for x in compilableDict]
    for apr in aprs:
        print(apr)
        # Number of compilable patches
        print(len(compilableDict[apr]))
        # Number of plausible patches
        print(len(plausibleDict[apr]))
        # Number of compilable bugs
        tmp = set([x[:x.rindex('-')] for x in compilableDict[apr]])
        print(len(tmp))
        # Number of plausible bugs
        tmp = set([x[:x.rindex('-')] for x in plausibleDict[apr]])
        print(len(tmp))
        # # print out the list of plausible patches for each par
        print('{}D4jPlausiblePatches={}'.format(apr, plausibleDict[apr]))

START = time.time()

if __name__ == '__main__':
    if len(sys.argv) != 2:
        print('Usage: python3 {} <command>'.format(sys.argv[0]))
        print('    command: "validate" or "analyze"')
        sys.exit(1)
    try:
        if sys.argv[1] == 'validate':
            main()
        elif sys.argv[1] == 'analyze':
            analyzeValidationResultForAll()  # will print the validation result
        # print('========1.2.0=========')
        # analyzeValidationResultForAll(d4jVersion='1.2.0')
        # print('========2.0.0=========')
        # analyzeValidationResultForAll(d4jVersion='2.0.0')
    finally:
        for cmd, cwd, process, logFileObj, dirSlotId, processName, fileToBeReplacedPath in processPool:
            if logFileObj is not None:
                logFileObj.close()
            process.kill()
        shutil.rmtree(str(d4jMvnProjTmpDir), ignore_errors=True)