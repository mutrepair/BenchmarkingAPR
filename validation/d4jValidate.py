import os, sys, shutil
from pathlib import Path
import subprocess as sp

def contentInFile(content:str, file:Path):
    with file.open() as f:
        for line in f:
            if content in line:
                return True
    return False

def d4jValidate(projPath: Path, logFilePrefix:str, targetDirPath=None):

    if targetDirPath is None:
        targetDirPath = sp.check_output("defects4j export -p dir.bin.classes", shell=True, universal_newlines=True, cwd=str(projPath)).strip()

    if 'gson' in projPath.name.lower():
        patchesPoolDir = projPath / 'gson' / 'patches-pool'
    else:
        patchesPoolDir = projPath / 'patches-pool'
    for patchDir in patchesPoolDir.iterdir():
        if not patchDir.is_dir():
            continue
        patchId = patchDir.name

        # logFile
        targetLogFilePath = Path(logFilePrefix + '-' + patchId + '.log')
        targetLogFilePath.parent.mkdir(parents=True, exist_ok=True)

        if targetLogFilePath.exists() and (contentInFile('Failing tests: ', targetLogFilePath) or contentInFile('###TIMEOUT###', targetLogFilePath)):
            print('Patch {} is already finished, skipping'.format(patchId))
            continue

        classFilePaths = sp.check_output('find {} -name "*.class"'.format(patchDir), shell=True, universal_newlines=True).strip().split()

        # check whether the class files have the same parent dir
        classFileDirPath = Path(classFilePaths[0]).parent
        for classFilePath in classFilePaths:
            assert str(Path(classFilePath).parent) == str(classFileDirPath)
        
        classFileDirRelatedPath = os.path.relpath(str(classFileDirPath), str(patchDir))
        targetClassFileDirPath = projPath / targetDirPath / classFileDirRelatedPath

        # backup the target class file to be replaced
        classPathsNeedToRecover = []
        for classFile in classFilePaths:
            className = Path(classFile).name
            targetClassFilePath = targetClassFileDirPath / className
            assert targetClassFileDirPath.exists()
            classPathsNeedToRecover.append(str(targetClassFilePath))
        for p in classPathsNeedToRecover:
            if not os.path.isfile(p + '.bak'):
                shutil.copyfile(p, p + '.bak')

        for p in classPathsNeedToRecover:
            assert Path(p).exists()

        try:
            # apply the patch
            sp.run('cp {}/* {}/'.format(classFileDirPath, targetClassFileDirPath), check=True, shell=True, universal_newlines=True)

            with open(targetLogFilePath, 'w') as f:
                # validation
                print('Validation start for patch {}-{}'.format(projPath.name, patchId))
                # originally `defects4j test`` for collections only takes 15-18s (wall clock time), here set the time out to 60s
                process = sp.run('timeout 120 defects4j test'.split(), cwd=str(projPath), universal_newlines=True, stderr=f, stdout=f)
                retCode = process.returncode
                print('Validation for patch {}@{} return code: {}'.format(projPath.name, patchId, retCode))
                if retCode == 124:
                    f.write('###TIMEOUT###')
        finally:
            # recover the class files after validation
            for p in classPathsNeedToRecover:
                shutil.copyfile(p + '.bak', p)

if __name__ == '__main__':
    assert len(sys.argv) == 4  # validation project path, logFilePrefix, projectTargetDirPath
    projPath = Path(sys.argv[1]).resolve()
    d4jValidate(projPath, sys.argv[2], targetDirPath=sys.argv[3] if sys.argv[3] != 'None' else None)
