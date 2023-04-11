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

d4j120Projects = ["chart", "closure", "lang", "math", "mockito", "time"]
d4j200Projects = ["cli", "codec", "collections", "compress", "csv", "gson", "jacksoncore", "jacksondatabind", "jacksonxml", "jsoup", "jxpath"]

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
            cmd, cwd, process, logFileObj, dirSlotId, processName, fileToBeReplacedPath = element
            retCode = process.poll()
            # haven't finished yet
            if retCode is None:
                continue
            else:
                print('=' * 10 + ' Process {}: `{}` in "{}" Finished '.format(processName, cmd, cwd) + '=' * 10)
                print('=' * 10 + ' {} retCode: {} '.format(processName, retCode) + '=' * 10)
                projName, mid = processName.split('-')

                processPool.remove(element)
                dirSlot[dirSlotId] = 0

                try:
                    # recover replaced file
                    shutil.copy(str(fileToBeReplacedPath) + '.bak', str(fileToBeReplacedPath))
                except:
                    import traceback
                    traceback.print_exc()
                    print('Running "git checkout -- ."')
                    sp.run('git checkout -- .', shell=True, cwd=cwd)
                
                if logFileObj is not None:
                    logFileObj.close()

                global currentApr

                if retCode == 0:
                    global finishedMids
                    finishedMids.append(mid)
                #     plausibleList = getPlausibleListFromValidationLog(Path(logFileObj.name))
                #     validationResultDict[currentApr][projName + '-' + mid] = plausibleList
                # else:
                #     validationResultDict[currentApr][projName + '-' + mid] = 'FAIL'

                print('Found {} process in pool running'.format(len(processPool)))
                print('Time elpsed: {} s'.format(time.time() - START))
                break

def waitForProcessPoolSlotAvailable():
    waitUntilMultiProcessLessThan(maxMultiProcess)

def waitForProcessPoolFinish():
    waitUntilMultiProcessLessThan(1)

# =============================================================================================

originalD4jProjDirPath = Path(configDict['mutationBenchmarkPath']).resolve()

logDirPath = (Path(configDict['logDirPath'])).resolve()
logDirPath.mkdir(exist_ok=True)

validationFinishedMidListDirPath = Path(configDict['finishedDirPath']).resolve()
validationFinishedMidListDirPath.mkdir(exist_ok=True)

d4jMvnProjDir = Path(configDict['d4jMvnProjDir']).resolve()
d4jMvnProjTmpDir = Path(configDict['d4jMvnProjTmpDir']).resolve()
# patchesDirPath = Path('tbar_patches').resolve()

# with open("validationConfig.yaml", 'r') as stream:
#     patchesPathDict = yaml.safe_load(stream)
# for key in patchesPathDict:
#     patchesPathDict[key] = Path(patchesPathDict[key]).resolve()

finishedMids = []
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

def file2Lines(filePath: Path):
    res = []
    with filePath.open() as f:
        for line in f:
            res.append(line.strip())
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


def tryValidation(d4jMvnProjToCopy:Path, midPatchDir: Path, srcRelativePath:str, buildRelativePath:str, logPath: Path, projName: str, mid: str, projPath: Path):
    if len(processPool) >= maxMultiProcess:
        waitForProcessPoolSlotAvailable()

    processName = "{}-{}".format(projName, mid)

    # select slot
    assert min(dirSlot) == 0  # there must be at least 1 available slot because of waitForProcessPoolSlotAvailable()
    for idx, value in enumerate(dirSlot):
        if value == 0:
            dirSlotId = idx
            break

    targetDir = str(d4jMvnProjToCopy) + '-' + str(dirSlotId)

    print("targetDir: " + targetDir)

    # apply mutant!
    fileToBeReplacedPath, javaFileRelativePath = applyMutant(Path(targetDir), projPath, mid, srcRelativePath=srcRelativePath)
    sp.run('defects4j compile'.split(), cwd=targetDir, check=True, universal_newlines=True)

    # prepare patches-pool
    patchesPoolPath = os.path.join(targetDir, 'gson', 'patches-pool') if 'gson-' in processName else os.path.join(targetDir, 'patches-pool')
    if os.path.isdir(patchesPoolPath):
        shutil.rmtree(patchesPoolPath)
    shutil.copytree(str(midPatchDir), patchesPoolPath)

    # For Collections, Uniapr can not find 2/3 tests executed by defects4j test, therefore use defects4j test to validate instead.
    if projName == 'collections':

        logFilePrefix = str(Path(logPath).parent / 'd4j-{}-{}'.format(projName, mid))

        cmd = 'python3 d4jValidate.py {} {} {}'.format(targetDir, logFilePrefix, buildRelativePath).split()
        cwd = '.'
        dirSlot[dirSlotId] = 1
        process = sp.Popen(cmd, shell=False, cwd=cwd, universal_newlines=True)  # shell=False by default
        processPool.append((cmd, cwd, process, None, dirSlotId, processName, fileToBeReplacedPath))
        print("Process {}: \"{}\"@\"{}\" has started and been added to the pool, using slot {}.".format(processName, cmd, cwd, str(dirSlotId)))
        return True
    else:

        if not logPath.parent.exists():
            logPath.parent.mkdir(parents=True, exist_ok=True)
        logFileObj = logPath.open(mode='w')

        cmd = 'timeout 3600 mvn org.uniapr:uniapr-plugin:1.0-SNAPSHOT:validate -Dd4jAllTestsFile=./all_tests -DrestartJVM=true'.split()
        cwd = targetDir if 'gson-' not in processName else os.path.join(targetDir, 'gson')
        dirSlot[dirSlotId] = 1
        process = sp.Popen(cmd, shell=False, stdout=logFileObj, stderr=logFileObj, cwd=cwd, universal_newlines=True)  # shell=False by default
        processPool.append((cmd, cwd, process, logFileObj, dirSlotId, processName, fileToBeReplacedPath))
        print("Process {}: \"{}\"@\"{}\" has started and been added to the pool, using slot {}.".format(processName, cmd, cwd, str(dirSlotId)))
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
    for apr in aprs:
        patchesDirPath = patchesPathDict[apr]
        if not patchesDirPath.exists():
            print('[ERROR] {} patch dir does not exists: {}'.format(apr, str(patchesDirPath)))
            continue
        validationResultDict[apr] = {}
        global currentApr
        currentApr = apr
        print('%' * 10 + " Start APR " + apr + ' ' + '%' * 10)
        for projPath in allProjs:
            try:
                m = re.match(r'(\w+)-(\d+f)', projPath.stem)
                assert m is not None
                projName = m[1]

        # # ======================================
        #         if projName != 'time':
        #             continue
        # # ======================================

                global finishedMids
                finishedMids = []
                targetFinishedMidListFile = validationFinishedMidListDirPath / apr / (projName + '.txt') 
                if targetFinishedMidListFile.exists():
                    finishedMids = file2Lines(targetFinishedMidListFile)

                print('=' * 10 + "Start " + str(projName) + '=' * 10)
                version = m[2]
                formalProjName = getProjFormalNameFromProjSimpleName(projName)

                targetD4jMvnProjPath = d4jMvnProjDir / formalProjName / version[:-1]
                if not targetD4jMvnProjPath.exists():
                    print("{} does not exist, skipping {}".format(str(targetD4jMvnProjPath), projName))
                    continue
                patchesDir = patchesDirPath / projName
                if not patchesDir.exists():
                    print("{} does not exist, skipping {}".format(str(patchesDir), projName))
                    continue
                # if not os.path.isdir(str(d4jMvnProjTmpDir / projName)):
                #     shutil.copytree(str(targetD4jMvnProjPath), str(d4jMvnProjTmpDir / projName))

                srcRelativePath = None
                buildRelativePath = None

                midPatchDirs = []
                for dir in patchesDir.iterdir():
                    if dir.is_dir():
                        midPatchDirs.append(dir)

                for midPatchDir in midPatchDirs:
                    mid = midPatchDir.stem
                    logPath = logDirPath / apr / ("{}-{}.log".format(projName, mid))
                    if mid in finishedMids:
                        if projName == 'collections':
                            continue
                        elif logPath.exists():
                            if contentInFile("Found 0 test units", logPath):
                                print('[Warning] {}-{} was validated before, but "Found 0 test units"'.format(projName, mid))
                            # elif contentInFile("Running test cases is terminated due to TIME_OUT", logPath):
                            #     print('[Warning] {}-{} was validated before, but has TIME_OUT patches. It will be validated again.'.format(projName, mid))
                            elif contentInFile("Directory of plausible patches", logPath):
                                print('{}-{} already finished validation before, skipping...'.format(projName, mid))
                                continue
                    # prepare slots dir
                    for i in range(maxMultiProcess):
                        slotPath = d4jMvnProjTmpDir / (projName + '-' + str(i))
                        if not slotPath.exists():
                            shutil.copytree(str(targetD4jMvnProjPath), str(slotPath))
                    if srcRelativePath is None:
                        srcRelativePath=getD4jProjSrcRelativePath(projPath)
                    if buildRelativePath is None:
                        buildRelativePath=getD4jProperty(projPath, 'dir.bin.classes')
                    tryValidation(d4jMvnProjTmpDir / projName, midPatchDir, srcRelativePath, buildRelativePath, logPath, projName, mid, projPath)

                waitForProcessPoolFinish()
                # deleting the slots dirs
                for i in range(maxMultiProcess):
                    slotPath = d4jMvnProjTmpDir / (projName + '-' + str(i))
                    if slotPath.exists():
                        shutil.rmtree(str(slotPath))
                print('=' * 10 + str(projName) + ' Finished' + '=' * 10)

            finally:
                writeToFile(set(finishedMids), targetFinishedMidListFile)
    # dumpValidationResult()

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

def analyzeValidationResultForAll(d4jVersion='all'):
    midDict = getSampledMids()
    compilableDict = {}
    plausibleDict = {}
    errorDict = {}
    for techDir in logDirPath.iterdir():
        if not techDir.is_dir():
            continue
        apr = techDir.name
        # if 'distribution' not in apr:
        #     continue
        # initialize dicts
        compilableDict[apr] = {}
        plausibleDict[apr] = {}
        errorDict[apr] = []
        # check the validation results except collections
        for logFile in techDir.iterdir():
            if not logFile.name.endswith('.log') or logFile.name.startswith('d4j'):
                continue
            projName, mid = logFile.stem.split('-')
            id = projName + '-' + mid
            compilableDict[apr][id] = []
            plausibleDict[apr][id] = []
            with logFile.open() as f:
                for line in f:
                    if line.startswith('>>Validating patchID:'):
                        m = re.match(r'>>Validating patchID: \d+ \(patch directory name: (\d+)\)\n', line)
                        assert m is not None
                        patchId = m[1]
                        compilableDict[apr][id].append(patchId)
                    elif line.startswith('Directory of plausible patches: '):
                        tmp = line.strip().split(':')[1].strip()
                        if tmp == '[]':
                            break
                        else:
                            plausibleDict[apr][id] = tmp[1:-1].split(', ')
                    # elif "BUILD FAILURE" in line:
                    #     compilableDict[apr][id] = []
                    #     plausibleDict[apr][id] = []
                    #     errorDict[apr].append(id)
                    #     break
                if len(compilableDict[apr][id]) == len(plausibleDict[apr][id]):
                    print('{}-{}: all {} compilable patches are plausible'.format(apr, id, len(plausibleDict[apr][id])))
            continue
        # check the validation result for collections
        mutantIds = sp.check_output("ls d* | cut -d'-' -f2,3 | sort | uniq", shell=True, cwd=str(techDir), universal_newlines=True).strip().split()
        for mutant in mutantIds:
            compilableDict[apr][mutant] = []
            plausibleDict[apr][mutant] = []
            [projName, mid] = mutant.split('-')
            for logFile in techDir.iterdir():
                if not logFile.name.endswith('.log') or not logFile.name.startswith('d4j-' + mutant):
                    continue
                patchId = logFile.stem.split('-')[-1]
                compilableDict[apr][mutant].append(patchId)
                if contentInFile('Failing tests: 0', logFile):
                    plausibleDict[apr][mutant].append(patchId)
            if len(compilableDict[apr][mutant]) == len(plausibleDict[apr][mutant]):
                    print('{}-{}: all {} compilable patches are plausible'.format(apr, mutant, len(plausibleDict[apr][mutant])))

    aprs = [x for x in compilableDict]
    expectedIds = [projName + '-' + mid for mid in midDict[projName] for projName in midDict]
    for apr in aprs:
        print(apr)
        compilablePatchesList = []
        for id in compilableDict[apr]:
            for patchId in compilableDict[apr][id]:
                compilablePatchesList.append(id + '-' + patchId)
        hasCompilablePatchesMidList = [id for id in compilableDict[apr] if len(compilableDict[apr][id]) > 0]
        plausiblePatchesList = []
        for id in plausibleDict[apr]:
            for patchId in plausibleDict[apr][id]:
                plausiblePatchesList.append(id + '-' + patchId)
        hasPlausiblePatchesMidList = [id for id in plausibleDict[apr] if len(plausibleDict[apr][id]) > 0]
        validationErrorMidList = errorDict[apr]
        print('    ' + '# compilable patches: ' + str(len(compilablePatchesList)))
        print('    ' + '# plausible patches: ' + str(len(plausiblePatchesList)))
        print('    ' + '# mutants having compilable: ' + str(len(hasCompilablePatchesMidList)))
        print('    ' + '# mutants having plausible: ' + str(len(hasPlausiblePatchesMidList)))
        print('    ' + '# mutants validation error: ' + str(len(validationErrorMidList)))

        if d4jVersion == '1.2.0':
            compilablePatchesList = [x for x in compilablePatchesList if x[:x.index('-')] in d4j120Projects]
            plausiblePatchesList = [x for x in plausiblePatchesList if x[:x.index('-')] in d4j120Projects]
            hasCompilablePatchesMidList = [x for x in hasCompilablePatchesMidList if x[:x.index('-')] in d4j120Projects]
            hasPlausiblePatchesMidList = [x for x in hasPlausiblePatchesMidList if x[:x.index('-')] in d4j120Projects]
        elif d4jVersion == '2.0.0':
            compilablePatchesList = [x for x in compilablePatchesList if x[:x.index('-')] in d4j200Projects]
            plausiblePatchesList = [x for x in plausiblePatchesList if x[:x.index('-')] in d4j200Projects]
            hasCompilablePatchesMidList = [x for x in hasCompilablePatchesMidList if x[:x.index('-')] in d4j200Projects]
            hasPlausiblePatchesMidList = [x for x in hasPlausiblePatchesMidList if x[:x.index('-')] in d4j200Projects]
        print(str(len(compilablePatchesList)))
        print(str(len(plausiblePatchesList)))
        print(str(len(hasCompilablePatchesMidList)))
        print(str(len(hasPlausiblePatchesMidList)))

        # print('{}PlausiblePatches = {}'.format(apr, str(plausiblePatchesList)))
        print()

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