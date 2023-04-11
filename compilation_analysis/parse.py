from pathlib import Path
import sys

resDict = {}
totalDict = {}
targetFile = Path(sys.argv[1])

def getkv(line):
    # print(line)
    idx = line.rindex(':')
    key = line[:idx]
    v = line[idx+1:]
    key = key.strip()
    v = v.strip()
    return key, v

currentTool = None
errorStart = False
with targetFile.open() as f:
    for line in f:
        if line.strip() == ('All tools'):
            break
        if line.startswith('Analyzing '):
            currentTool = line.split()[1].strip()
            errorStart = False
            if currentTool not in resDict:
                resDict[currentTool] = {}
            continue
        elif line.startswith('bugs with no '):
            k, v = getkv(line)
            resDict[currentTool][k] = v
        elif line.strip().endswith(']'):
            errorStart  = True
        elif ':' in line and errorStart:
            k, v = getkv(line)
            # if k not in totalDict:
            #     totalDict[k] = 0
            # else:
            #     totalDict[k] += int(v)
            totalDict[k] = totalDict.get(k, 0) + int(v)
            
            resDict[currentTool][k] = v

# print(resDict)

k = 15
tmp = [ (x, totalDict[x]) for x in totalDict]
tmp.sort(key=lambda x:x[1], reverse=True)
patterns = [p[0] for p in tmp][:k]
for p in patterns:
    print(p)

print()
# print(patterns)
for tool in resDict:
    print()
    print(tool)
    for p in patterns:
        if p not in resDict[tool]:
            print(0)
        else:
            print(resDict[tool][p])