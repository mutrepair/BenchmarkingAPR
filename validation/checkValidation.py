"""
If the number of executed tests (in all_tests file) by d4j is different from that by Uniapr (validation log), rerun validation use d4j
"""

import re
from pathlib import Path
import subprocess as sp

d4jMvnProjDir = Path('d4jMvnForUniapr/d4jMvnProj/').resolve()
logPathDir = Path('logs').resolve()

# def checkTestNum():
#     res = set()
#     checked = set()
#     for dir in d4jMvnProjDir.iterdir():
#         projName = dir.name.lower()
#         projAllTestFile = sp.check_output('find {} -maxdepth 3 -name all_tests'.format(str(dir)), shell=True, universal_newlines=True).strip()
#         d4jTestNum = int(sp.check_output('cat {} | wc -l'.format(str(projAllTestFile)), shell=True, universal_newlines=True).strip())
#         for aprLogDir in logPathDir.iterdir():
#             if not aprLogDir.is_dir():
#                 continue
#             for logFile in aprLogDir.iterdir():
#                 if not logFile.name.startswith(projName) or not logFile.is_file() or not logFile.name.endswith('.log'):
#                     continue
#                 foundLine = sp.check_output('grep -E "^Found [0-9]+ test units" {} | head -1'.format(str(logFile)), shell=True, universal_newlines=True).strip()
#                 m = re.match(r'Found (\d+) test units', foundLine)
#                 assert m is not None
#                 uniaprTestNum = int(m[1])
#                 if d4jTestNum != uniaprTestNum:
#                     print('ERROR: {}: d4j {} -- uniapr {}'.format(projName, d4jTestNum, uniaprTestNum))
#                     res.add(projName)
                
def checkTestNum():
    res = set()
    checked = set()
    for dir in d4jMvnProjDir.iterdir():
        projName = dir.name.lower()
        projAllTestFile = sp.check_output('find {} -maxdepth 3 -name all_tests'.format(str(dir)), shell=True, universal_newlines=True).strip()
        d4jTestNum = int(sp.check_output('cat {} | wc -l'.format(str(projAllTestFile)), shell=True, universal_newlines=True).strip())
        for aprLogDir in logPathDir.iterdir():
            if not aprLogDir.is_dir():
                continue
            for logFile in aprLogDir.iterdir():
                if not logFile.name.startswith(projName) or not logFile.is_file() or not logFile.name.endswith('.log'):
                    continue
                foundLine = sp.check_output('grep -E "^Found [0-9]+ test units" {} | head -1'.format(str(logFile)), shell=True, universal_newlines=True).strip()
                m = re.match(r'Found (\d+) test units', foundLine)
                assert m is not None
                uniaprTestNum = int(m[1])
                if d4jTestNum != uniaprTestNum:
                    print('ERROR: {}: d4j {} -- uniapr {}'.format(projName, d4jTestNum, uniaprTestNum))
                    res.add(projName)
                break
            break
    print(res)

if __name__ == '__main__':
    checkTestNum()