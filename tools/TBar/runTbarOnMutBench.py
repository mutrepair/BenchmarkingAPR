import os, re, sys
import shutil
import subprocess as sp
from pathlib import Path

processPool = []  # storing (cmd, cwd, process, logFileObj)
maxMultiProcess = 8

def runCmdAndWaitForFinish(cmd: list, cwd=None):
    process = sp.Popen(cmd, shell=False, stdout=sp.PIPE, stderr=sp.PIPE, cwd=cwd, universal_newlines=True)
    stdout, stderr = process.communicate()
    retCode = process.poll()
    return stdout, stderr, retCode

# Warning: if the logPath is not set, the process may stuck sometimes. See https://stackoverflow.com/a/39607358/11495796
def tryRunCmdWithProcessPool(cmd: list, logPath: str, cwd=None, insist=True):
    if len(processPool) >= maxMultiProcess:
        if insist:
            waitForProcessPoolSlotAvailable()
        else:
            return False
    stdout = sp.PIPE
    stderr = sp.PIPE
    logFileObj = None
    if logPath is not None:
        logFileObj = open(logPath, 'w')
        stdout = logFileObj
        stderr = logFileObj
    process = sp.Popen(cmd, shell=False, stdout=stdout, stderr=stderr, cwd=cwd, universal_newlines=True)  # shell=False by default
    cwd = '.' if cwd is None else cwd
    processPool.append((cmd, cwd, process, logFileObj))
    print("Process \"{}\"@\"{}\" has started and been added to the pool.".format(cmd, cwd))
    return True

def waitUntilMultiProcessLessThan(t):
    if len(processPool) >= t:
        print("[INFO] Waiting the number of parallel processes become less than " + str(t))
    while len(processPool) >= t:
        for cmd, cwd, process, logFileObj in processPool:
            retCode = process.poll()
            # haven't finished yet
            if retCode is None:
                continue
            else:
                print('=' * 10 + ' `{}` in "{}" Finished '.format(cmd, cwd) + '=' * 10)
                print('*' * 10 + ' RetCode: ' + str(retCode) + ' ' + ('*' * 10))
                # stdout, stderr = process.communicate()
                # print('*' * 10 + ' STDOUT ' + ('*' * 10))
                # if stdout is not None and len(stdout) > 0:
                    # print(stdout)
                # print('*' * 10 + ' STDERR ' + ('*' * 10))
                # if stderr is not None and len(stderr) > 0:
                    # print(stderr)
                print('=' * (20 + len(' `{}` in "{}" Finished '.format(cmd, cwd))))
                processPool.remove((cmd, cwd, process, logFileObj))
                if logFileObj is not None:
                    logFileObj.close()
                print('Found {} process in pool running'.format(len(processPool)))
                break

def waitForProcessPoolSlotAvailable():
    waitUntilMultiProcessLessThan(maxMultiProcess)

def waitForProcessPoolFinish():
    waitUntilMultiProcessLessThan(1)

# =============================================================================================

originalD4jProjDirPath = Path('../d4jProj').resolve()
tbarD4jProjDirPath = Path('tbarD4jProj').resolve()  # copy the project need to be fixed here
tbarD4jProjDirPath.mkdir(exist_ok=True)
bugPositionFile = Path('MutBenchBugPositions.txt').resolve()
patchesDirPath = Path('patches-1700').resolve()
logDirPath = (Path('logs') / 'generation').resolve()
logDirPath.mkdir(exist_ok=True)
d4jHome = '/home/xxx/research/apr/experiments/defects4j/'

def getFinishedProjPath():
    res = []
    for dir in originalD4jProjDirPath.iterdir():
        if dir.is_dir() and (dir / 'sampledMutIds.txt').exists():
            res.append(dir)
    return res

def file2Lines(filePath: Path):
    res = []
    with filePath.open() as f:
        for line in f:
            res.append(line.strip())
    return res

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

# ======= For Distribution =======
newSample={'collections': ['6152', '8112', '8343', '8216', '8348', '8108', '10087', '6148', '8492', '11864', '2652', '1508'], 'time': ['18136', '10081', '15493', '4864', '658', '12449', '2556', '19225', '10160', '2551', '4856', '8960', '11572'], 'math': ['94078', '118483', '94440', '108308', '118180', '94376', '115465', '41781', '94185', '117598', '115827', '94174', '92468', '109334', '118484', '94153', '117670', '108457', '107938', '118195', '115826', '35228', '16011', '118026', '27082', '1872', '35785', '39490', '116617', '95878', '35937', '111244', '110625', '39136', '74852', '110955', '64974', '63972', '22130', '7710', '116154', '101658', '110936', '117041', '84963', '35938', '109722', '38034', '111968', '17424', '15714', '34840', '41844', '60729', '95820', '57451', '117283', '81535', '19560', '117420', '117580', '111278', '103223', '34656', '119014', '19561', '35959', '5010', '108351', '27140', '113518', '48177', '108453', '32583', '111085', '111050', '103968', '96327', '77143', '58288', '116783', '14349', '42114', '15302', '14347', '80734', '36958', '8954', '16176', '3463', '62416', '118638', '42068', '94205', '117666', '94240', '94373', '93203', '66445', '118059', '14098', '108626', '18388', '118366', '118158', '62404', '94364', '59422', '93255', '118550', '108819', '2977'], 'codec': ['288', '691', '690', '669', '652', '1017', '497', '4217', '261', '297', '469', '316'], 'chart': ['70393', '70533', '34236', '76686', '34237', '54929'], 'jacksondatabind': ['927', '912', '7353', '40'], 'closure': ['48813', '31081', '31096', '13793', '30680', '13710', '36346', '90', '13942', '30743', '31012', '13724', '13866', '30845', '63', '36345', '144', '13957', '48224', '11209', '36349', '30923', '30740', '11445', '33315', '31023', '30953', '30739', '48791', '30709', '30952'], 'jacksonxml': ['292'], 'jxpath': ['12215', '9840', '7840', '10391', '10689', '10804', '8246', '9432', '10053', '7850', '10309', '7922'], 'jacksoncore': ['16309', '1370', '13101', '15722', '5817', '14170', '15781', '10690', '13052', '621', '15840', '10484', '1806', '15991', '15886', '15841', '10570', '11378', '345', '4570', '15799', '13888', '10988', '10606', '10516', '2147', '5447', '1367', '103', '12131', '290', '1366', '12861', '4441', '11980', '11868', '834', '6772', '11218', '3267', '16146', '10463', '12996', '15778', '7347', '3278', '14434', '11219', '6093', '14104', '14220', '12062'], 'compress': ['5465', '1623', '4844', '7964', '5574', '5847', '4899', '5371', '6028', '5858', '6121', '1504', '4853', '7940', '4816', '7966', '6174', '3497', '1607', '6128'], 'lang': ['7857', '7790', '7154', '6308', '7824', '21179', '15195', '15107', '14961', '15151', '17101', '6724', '6310', '15181', '5887', '5886', '7700', '7879', '3503', '6726', '16696']}
# ======= ================ =======

def genMutBenchBugPositions(targetFile=Path('./MutBenchBugPositions.txt'), mutatorDistribution=False):
    positions = []
    for projPath in getFinishedProjPath():
        projName = getProjNameFromProjPath(projPath)
        # ======= For Distribution =======
        if mutatorDistribution:
            if projName not in newSample:
                continue
        # ======= ================ =======
        mutLog = projPath / 'mutants.log'
        sampleTxt = projPath / 'sampledMutIds.txt'
        # print(str(projPath))
        assert mutLog.exists()
        assert sampleTxt.exists()
        mids = file2Lines(sampleTxt)
        # ======= For Distribution =======
        if mutatorDistribution:
            mids = newSample[projName]
        # ======= ================ =======
        minfos = file2Lines(mutLog)
        projSrcRelativePath = getD4jProperty(projPath, 'dir.src.classes')
        for mid in mids:
            info = minfos[int(mid)-1]
            assert info.startswith(mid + ":")
            m = re.match(r'.+:(.*?)@.*:(\d+):.+', info)
            assert m is not None
            buggyFileRelativePath = str(Path(projSrcRelativePath) / (m[1].replace('.', '/') + '.java'))
            buggyLineNum = m[2]
            positions.append('{}_{}@{}@{}'.format(projName, mid, buggyFileRelativePath, buggyLineNum))
    with targetFile.open(mode='w') as f:
        for line in positions:
            f.write(line + '\n')


def applyMutant(targetProjPath: Path, originalProjPath: Path, mid: str, srcRelativePath=None):
    # reset the targetProjPath
    sp.run('git checkout -- .', shell=True, universal_newlines=True, check=True, cwd=str(targetProjPath))
    # get the path of mutant java file
    mutantsDir = originalProjPath / 'mutants' / mid
    assert mutantsDir.exists()
    javaFileRelativePath = sp.check_output('find . -name *.java', shell=True, universal_newlines=True, cwd=str(mutantsDir)).strip()
    mutantPath = mutantsDir / javaFileRelativePath
    # get the patch of the java file to be replaced
    srcRelativePath = srcRelativePath if srcRelativePath is not None else getD4jProjSrcRelativePath(targetProjPath)
    fileToBeReplacedPath = targetProjPath / srcRelativePath / javaFileRelativePath
    shutil.copy(str(fileToBeReplacedPath), str(fileToBeReplacedPath) + '.bak')
    shutil.copy(str(mutantPath), str(fileToBeReplacedPath))
    sp.run("diff -s {} {}".format(str(fileToBeReplacedPath) + '.bak', str(fileToBeReplacedPath)), shell=True, universal_newlines=True)
    return str(fileToBeReplacedPath), javaFileRelativePath

def getMutFixedFileContent(projPath: Path, mutId: str, projSrcPath=None):
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
                    return f.read()

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

def getFileLine(path: Path, lineNum: int):
    cnt = 1
    with path.open() as f:
        for line in f:
            if cnt == lineNum:
                return line
            cnt += 1
    print("{} do not have line number {}".format(str(path), lineNum))
    return None

def getMutator(projPath: Path, mutId: str):
    mutLog = projPath / 'mutants.log'
    assert mutLog.exists()
    with mutLog.open() as log:
        for line in log:
            if line.startswith(mutId + ':'):
                m = re.match(r'\d+:(\w+):.*\n', line)
                assert m is not None
                return m[1]

def isExactlySameCode(a:str, b:str):
    tmp1 = ''.join(a.split())
    tmp2 = ''.join(b.split())
    return tmp1 == tmp2

def checkAllPatches():
    mutatorDict = {}
    resDict = {}
    correctPatchIdList = []
    for projPath in getFinishedProjPath():
        m = re.match(r'(\w+)-(\d+f)', projPath.stem)
        assert m is not None
        projName = m[1]
        # if projName != "jacksoncore":
        #     continue
        srcRelativePath=getD4jProjSrcRelativePath(projPath)
        print('='*15 + projName + '='*15)
        for dir in patchesDirPath.iterdir():
            if dir.is_dir() and dir.stem.startswith(projName):
                mid = dir.stem.split('_')[1]
                print('Checking ' + dir.stem)
                for idDir in (dir / 'patches-pool').iterdir():
                    if idDir.is_dir():
                        patchFilePath = sp.check_output("find . -name '*.java'", shell=True, universal_newlines=True, cwd=str(idDir)).strip()
                        patchFilePath = idDir / patchFilePath
                        # mutLineNum = getMutLineNum(projPath, mid)
                        # patchedLine = getFileLine(patchFilePath, mutLineNum)
                        with patchFilePath.open() as f:
                            patchFileContent  = f.read()
                        fixedJavaFileContent = getMutFixedFileContent(projPath, mid, projSrcPath=srcRelativePath)
                        if isExactlySameCode(patchFileContent, fixedJavaFileContent):
                            mutator = getMutator(projPath, mid)
                            if mutator not in mutatorDict:
                                mutatorDict[mutator] = []
                            mutatorDict[mutator].append(projName + '-' + mid)
                            if projName not in resDict:
                                resDict[projName] = []
                            resDict[projName].append(mid)
                            # print("{} fixed!".format(dir.stem))
                            patchIdentifier = '{}-{}-{}'.format(projName, mid, idDir.name)
                            print('Found syntactically equivalent patch (removing space): ' + patchIdentifier)
                            correctPatchIdList.append(patchIdentifier)
    # proj = [k for k in resDict]
    # proj.sort()
    # for key in proj:
    #     print("{} mutants of {} are correctly (exactly) fixed!".format(len(resDict[key]), key))
    # mutators = [k for k in mutatorDict]
    # mutators.sort()
    # for m in mutators:
    #     print("{} {}".format(m, mutatorDict[m]))
    # for m in mutators:
    #     print("{} {}".format(m, len(mutatorDict[m])))
    print('tbarCorrectPatches={}'.format(correctPatchIdList))

def getAllMutators():
    mutatorDict = {}
    for projPath in getFinishedProjPath():
        sampleTxt = projPath / 'sampledMutIds.txt'
        mids = file2Lines(sampleTxt)
        for mid in mids:
            mutator = getMutator(projPath, mid)
            if mutator not in mutatorDict:
                mutatorDict[mutator] = []
            mutatorDict[mutator].append(mid)
    mutators = [k for k in mutatorDict]
    mutators.sort()
    for m in mutators:
        print("{}: {}".format(m, len(mutatorDict[m])))

def tmp():
    for projPath in getFinishedProjPath():
        m = re.match(r'(\w+)-(\d+f)', projPath.stem)
        assert m is not None
        projName = m[1]
        version = m[2]
        formalProjName = getProjFormalNameFromProjSimpleName(projName)
        # print('defects4j checkout -p {0} -v {1} -w {2} || (echo "[ERROR] Failed to checkout {0}-{1}!" && exit 1 ) ;'.format(formalProjName, version, projName))
        print('echo "========== Start testing {} ==========="'.format(projName))
        print('cd $pwd && cd d4jProj/{} && time defects4j test'.format(projName))

def main(mutatorDistribution=False):
    for projPath in getFinishedProjPath():
        print('=' * 10 + str(projPath) + '=' * 10)
        srcRelativePath = None
        buildRelativePath = None
        sampleTxt = projPath / 'sampledMutIds.txt'
        assert sampleTxt.exists()
        m = re.match(r'(\w+)-(\d+f)', projPath.stem)
        assert m is not None
        projName = m[1]
        # ======= For Distribution =======
        if mutatorDistribution:
            if projName not in newSample:
                continue
        # ======= ================ =======
        version = m[2]
        formalProjName = getProjFormalNameFromProjSimpleName(projName)
        mids = file2Lines(sampleTxt)
        # ======= For Distribution =======
        if mutatorDistribution:
            mids = newSample[projName]
        # ======= ================ =======
        for mid in mids:
            # check if it is already finished before
            patchesInfoFile = patchesDirPath / '{}_{}'.format(projName, mid) / 'patches-pool' / 'patches.info'
            if patchesInfoFile.exists():
                print('Patches for {}_{} already exist, skipping...'.format(projName, mid))
                continue
            print('=' * 10 + 'Start {}_{}'.format(projName, mid) + '=' * 10)
            # checkout the original fixed version
            targetProjPath = tbarD4jProjDirPath / '{}_{}'.format(projName, mid)
            try:
                sp.run('defects4j checkout -p {} -v {} -w {}'.format(formalProjName, version, str(targetProjPath)), shell=True, universal_newlines=True, check=True)
            except:
                print('Try removing {} and checkout again...'.format(str(targetProjPath)))
                shutil.rmtree(str(targetProjPath), ignore_errors=True)
                sp.run('defects4j checkout -p {} -v {} -w {}'.format(formalProjName, version, str(targetProjPath)), shell=True, universal_newlines=True, check=True)
            
            if srcRelativePath is None or buildRelativePath is None:
                srcRelativePath=getD4jProjSrcRelativePath(projPath)
                buildRelativePath=getD4jProperty(projPath, 'dir.bin.classes')
            
            # apply the mutant file
            fileToBeReplacedPath, javaFileRelativePath = applyMutant(targetProjPath, projPath, mid, srcRelativePath=srcRelativePath)
            javacOutputDirPath = str((Path(targetProjPath) / buildRelativePath / javaFileRelativePath).parent)
            try:
                sp.run('defects4j compile', shell=True, universal_newlines=True, check=True, cwd=str(targetProjPath))
            except:
                try:
                    cmd = 'javac {} -cp {} -d {}'.format(fileToBeReplacedPath, buildRelativePath, javacOutputDirPath).split()
                    sp.run(cmd, shell=False, universal_newlines=True, check=True, cwd=str(targetProjPath))
                except:
                    print('[ERROR] The mutant {}-{} can not be compiled by defects4j.'.format(projName, mid))
                    continue
            # start the patch generation
            logPath = logDirPath / ('{}_{}.log'.format(projName, mid))
            tryRunCmdWithProcessPool('bash PerfectFLTBarRunner.sh {} {}_{} {} {}'.format(str(tbarD4jProjDirPath) + '/', projName, mid, d4jHome, str(bugPositionFile)).split(), logPath, insist=True)
        waitForProcessPoolFinish()
        print('=' * 10 + str(projPath) + ' Finished' + '=' * 10)
        for mid in mids:
            targetProjPath = tbarD4jProjDirPath / '{}_{}'.format(projName, mid)
            shutil.rmtree(str(targetProjPath), ignore_errors=True)

def runTbarOnSingleMutant(projName: str, mid: int):
    mid = str(mid)
    for projPath in getFinishedProjPath():
        if projPath.stem.startswith(projName + '-'):
            print('=' * 10 + str(projPath) + '=' * 10)
            srcRelativePath=getD4jProjSrcRelativePath(projPath)
            buildRelativePath=getD4jProperty(projPath, 'dir.bin.classes')
            sampleTxt = projPath / 'sampledMutIds.txt'
            assert sampleTxt.exists()
            m = re.match(r'(\w+)-(\d+f)', projPath.stem)
            assert m is not None
            projName = m[1]
            version = m[2]
            formalProjName = getProjFormalNameFromProjSimpleName(projName)
            
            # checkout the original fixed version
            targetProjPath = tbarD4jProjDirPath / '{}_{}'.format(projName, mid)
            sp.run('defects4j checkout -p {} -v {} -w {}'.format(formalProjName, version, str(targetProjPath)), shell=True, universal_newlines=True, check=True)
            # apply the mutant file
            fileToBeReplacedPath, javaFileRelativePath = applyMutant(targetProjPath, projPath, mid, srcRelativePath=srcRelativePath)
            javacOutputDirPath = str((Path(targetProjPath) / buildRelativePath / javaFileRelativePath).parent)
            try:
                sp.run('defects4j compile', shell=True, universal_newlines=True, check=True, cwd=str(targetProjPath))
            except:
                try:
                    cmd = 'javac {} -cp {} -d {}'.format(fileToBeReplacedPath, buildRelativePath, javacOutputDirPath).split()
                    sp.run(cmd, shell=False, universal_newlines=True, check=True, cwd=str(targetProjPath))
                except:
                    print('[ERROR] The mutant {}-{} can not be compiled by defects4j.'.format(projName, mid))
                    continue
            # start the patch generation
            cmd = 'bash PerfectFLTBarRunner.sh {} {}_{} {} {}'.format(str(tbarD4jProjDirPath) + '/', projName, mid, d4jHome, str(bugPositionFile)).split()
            # sp.run(cmd, shell=False, universal_newlines=True)
            logPath = logDirPath / ('{}_{}.log'.format(projName, mid))
            tryRunCmdWithProcessPool('bash PerfectFLTBarRunner.sh {} {}_{} {} {}'.format(str(tbarD4jProjDirPath) + '/', projName, mid, d4jHome, str(bugPositionFile)).split(), str(logPath), insist=True)
            waitForProcessPoolFinish()

def cleanPatches():
    expectedDirNames = set()  # format: chart_4370
    for projPath in getFinishedProjPath():
        sampleTxt = projPath / 'sampledMutIds.txt'
        assert sampleTxt.exists()
        m = re.match(r'(\w+)-(\d+f)', projPath.stem)
        assert m is not None
        projName = m[1]
        mids = file2Lines(sampleTxt)
        for mid in mids:
            expectedDirNames.add(projName + "_" + mid)
    assert len(expectedDirNames) == 1700
    for dir in patchesDirPath.iterdir():
        dirName = dir.stem
        if dirName not in expectedDirNames:
            print('[INFO] {} is not expected!'.format(dirName))
            # shutil.rmtree(str(dir))
            continue
    for dirName in expectedDirNames:
        expectedDir = patchesDirPath / dirName
        if not expectedDir.exists():
            print('[WARNING] No patch is generated for {}'.format(dirName))
            continue
        elif not (expectedDir / 'patches-pool' / 'patches.info').exists():
            print('[WARNING] Patches for {} is not complete! File patches.info is missing.'.format(dirName))
            continue

if __name__ == '__main__':
    try:
        if len(sys.argv) != 3:
            print('Usage: python3 {} <command> <D4J_HOME>'.format(sys.argv[0]))
            print('command: mutationBenchmark, mutatorDistribution')
            print('D4J_HOME: the defects4j home path, e.g., "/home/username/tools/defects4j/"')
            exit(1)
        command = sys.argv[1]
        d4jHome = str(Path(sys.argv[2]).resolve()) + '/'

        if command == 'mutationBenchmark':
            # the maximum number of processes that can be run at the same time
            maxMultiProcess = 8
            originalD4jProjDirPath = Path('../../MutationBenchmark').resolve()
            tbarD4jProjDirPath = Path('tbarD4jProj-mutationBenchmark').resolve()  # copy the project need to be fixed here
            tbarD4jProjDirPath.mkdir(exist_ok=True)
            bugPositionFile = Path('MutBenchBugPositions-mutation.txt').resolve()
            patchesDirPath = Path('tbar_patches-mutationBenchmark').resolve()
            logDirPath = (Path('logs') / 'mutationBenchmark').resolve()
            logDirPath.mkdir(exist_ok=True)
            main()

        elif command == 'mutatorDistribution':
            # the maximum number of processes that can be run at the same time
            maxMultiProcess = 8
            originalD4jProjDirPath = Path('../../MutationBenchmark').resolve()
            tbarD4jProjDirPath = Path('tbarD4jProj-mutatorDistribution').resolve()  # copy the project need to be fixed here
            tbarD4jProjDirPath.mkdir(exist_ok=True)
            bugPositionFile = Path('MutBenchBugPositions-mutator.txt').resolve()
            patchesDirPath = Path('tbar_patches-mutatorDistribution').resolve()
            logDirPath = (Path('logs') / 'mutatorDistribution').resolve()
            logDirPath.mkdir(exist_ok=True)
            main(mutatorDistribution=True)

    finally:
        for _, _, p, log in processPool:
            log.close()
            p.kill()
        shutil.rmtree(str(tbarD4jProjDirPath), ignore_errors=True)