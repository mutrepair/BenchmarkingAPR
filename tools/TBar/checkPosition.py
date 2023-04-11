
import os

positionFile = 'BugPositions.txt'


def checkInFile(string: str, targetFile: str):
    if not os.path.isfile(targetFile):
        # print('[WARNING] {} not exist'.format(targetFile))
        return True
    with open(targetFile, 'r') as file:
        if string in file.read():
            return True
        else:
            print('[ERROR] {} not in {}'.format(string, targetFile))
            return False

errCount = 0
with open(positionFile) as file:
    for line in file:
        line = line.strip()
        tmp = line.split('@')
        projName = tmp[0]
        filePath = tmp[1]
        lineNum = tmp[2]
        pid = projName.split('_')[0]
        bid = projName.split('_')[1]
        targetFile = 'SuspiciousCodePositions/' + projName + '/Covered.txt'
        if '/org/' in filePath:
            className = filePath[filePath.index('org/'):-5].replace('/', '.')
        if '/com/' in filePath:
            className = filePath[filePath.index('com/'):-5].replace('/', '.')
        if ',' in lineNum:
            for line in lineNum.split(','):
                if '-' in line:
                    start = int(line.split('-')[0])
                    end = int(line.split('-')[1])
                    for l in list(range(start, end+1)):
                        loc = '{}@{}'.format(className, l)
                        res = checkInFile(loc, targetFile)
                else:
                    loc = className + '@' + line
                    res = checkInFile(loc, targetFile)
        elif '-' in lineNum:
            start = int(lineNum.split('-')[0])
            end = int(lineNum.split('-')[1])
            for line in list(range(start, end+1)):
                loc = '{}@{}'.format(className, line)
                res = checkInFile(loc, targetFile)
        else:
            loc = className + '@' + lineNum
            res = checkInFile(loc, targetFile)
        if res == False:
            errCount += 1
print(errCount)