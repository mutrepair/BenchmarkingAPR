import sys
import os
import re
import json
import shutil
import subprocess as sp
from pathlib import Path

from myGenerator import prepareMutInputAllIn
from myGenerator import generatePatchesAllIn
from myGenerator import redirectOutErrToLogsAllin
from myGenerator import generateMeta
from myGenerator import rerank
from myGenerator import recoverOutErr
from myGenerator import getMutFixedLine

TESTER_DIR = os.path.abspath(__file__)[: os.path.abspath(__file__).rindex('/') + 1]
sys.path.append(TESTER_DIR + '../validation/')
from validate_defects4j import get_strings_numbers
sys.path.append(TESTER_DIR + '../dataloader/')
import tokenization

TESTER_DIR_PATH = Path(TESTER_DIR)
mutResultDirPath = TESTER_DIR_PATH / 'mutResults'
mutResultDirPath.resolve()
d4jProjDirPath = Path(TESTER_DIR + '../../../../dataset/MutationBenchmark')
d4jProjDirPath.resolve()
d4jProjPaths = []
for dir in d4jProjDirPath.iterdir():
    if dir.is_dir():
        d4jProjPaths.append(dir)
# print(d4jProjPaths)

def getSampledMutIdList(projPath: Path):
    res = []
    txt = projPath / 'sampledMutIds.txt'
    assert txt.exists()
    with txt.open() as f:
        for line in f:
            if len(line.strip()) > 0:
                res.append(line.strip())
    return res

def getMutator(projPath: Path, mutId: str):
    mutLog = projPath / 'mutants.log'
    assert mutLog.exists()
    with mutLog.open() as log:
        for line in log:
            if line.startswith(mutId + ':'):
                m = re.match(r'\d+:(\w+):.*\n', line)
                assert m is not None
                return m[1]

newSample={'collections': ['6152', '8112', '8343', '8216', '8348', '8108', '10087', '6148', '8492', '11864', '2652', '1508'], 'time': ['18136', '10081', '15493', '4864', '658', '12449', '2556', '19225', '10160', '2551', '4856', '8960', '11572'], 'math': ['94078', '118483', '94440', '108308', '118180', '94376', '115465', '41781', '94185', '117598', '115827', '94174', '92468', '109334', '118484', '94153', '117670', '108457', '107938', '118195', '115826', '35228', '16011', '118026', '27082', '1872', '35785', '39490', '116617', '95878', '35937', '111244', '110625', '39136', '74852', '110955', '64974', '63972', '22130', '7710', '116154', '101658', '110936', '117041', '84963', '35938', '109722', '38034', '111968', '17424', '15714', '34840', '41844', '60729', '95820', '57451', '117283', '81535', '19560', '117420', '117580', '111278', '103223', '34656', '119014', '19561', '35959', '5010', '108351', '27140', '113518', '48177', '108453', '32583', '111085', '111050', '103968', '96327', '77143', '58288', '116783', '14349', '42114', '15302', '14347', '80734', '36958', '8954', '16176', '3463', '62416', '118638', '42068', '94205', '117666', '94240', '94373', '93203', '66445', '118059', '14098', '108626', '18388', '118366', '118158', '62404', '94364', '59422', '93255', '118550', '108819', '2977'], 'codec': ['288', '691', '690', '669', '652', '1017', '497', '4217', '261', '297', '469', '316'], 'chart': ['70393', '70533', '34236', '76686', '34237', '54929'], 'jacksondatabind': ['927', '912', '7353', '40'], 'closure': ['31081', '31096', '13793', '30680', '13710', '36346', '90', '13942', '30743', '31012', '13724', '13866', '30845', '63', '36345', '144', '13957', '48224', '11209', '36349', '30923', '30740', '11445', '33315', '31023', '30953', '30739', '48791', '30709', '30952'], 'jacksonxml': ['292'], 'jxpath': ['12215', '9840', '7840', '10391', '10689', '10804', '8246', '9432', '10053', '7850', '10309', '7922'], 'jacksoncore': ['16309', '1370', '13101', '15722', '5817', '14170', '15781', '10690', '13052', '621', '15840', '10484', '1806', '15991', '15886', '15841', '10570', '11378', '345', '4570', '15799', '13888', '10988', '10606', '10516', '12376', '2147', '5447', '1367', '103', '12131', '290', '1366', '12861', '4441', '11980', '11868', '834', '6772', '11218', '3267', '16146', '10463', '12996', '15778', '7347', '3278', '14434', '11219', '6093', '14104', '14220', '12062'], 'compress': ['5465', '1623', '4844', '7964', '5574', '5847', '4899', '5371', '6028', '5858', '6121', '1504', '4853', '7940', '4816', '7966', '6174', '3497', '1607', '6128'], 'lang': ['7857', '7790', '7154', '6308', '7824', '21179', '15195', '15107', '14961', '15151', '17101', '6724', '6310', '15181', '5887', '5886', '7700', '7879', '3503', '6726', '16696']}

def main(generateForNewSample=False):
    for projPath in d4jProjPaths:
        projName = projPath.stem

        mutIds=getSampledMutIdList(projPath)

        if generateForNewSample:
            simpleProjName = projName.split('-')[0]
            if (simpleProjName not in newSample):
                continue
            mutIds = newSample[simpleProjName]
        
        print('=' * 10 + " Start {} ".format(projName) + '=' * 10)

        mutDataDir = mutResultDirPath / (projName + '-mutants-allin')
        if (mutDataDir / 'reranked_patches.json').exists():
            print('reranked_patches.json exists in {}'.format(projName))
            continue

        redirectOutErrToLogsAllin(projName)
        try:
            prepareMutInputAllIn(projPath, projName, mutIds=mutIds)
            generatePatchesAllIn(projName)
            generateMeta(projPath, projName, allin=True, mutIds=mutIds)
            rerank(projPath, projName, allin=True)
        except:
            import traceback
            traceback.print_exc()
        recoverOutErr()

def recoverPatches():
    # correctPatches = []
    # fixedDict = {}
    # mutatorDict = {}
    for dir in mutResultDirPath.iterdir():
        if dir.is_dir():
            resultJson = dir / 'reranked_patches.json'
            if resultJson.exists():
                print('='*10 + dir.stem + '='*10)
                projName = dir.stem.split('-')[0]
                projPathStem = re.match(r'(\w+?-\d+f).*', dir.stem)[1]
                projPath = d4jProjDirPath / projPathStem

                recoverPatches(projPath, resultJson, dir / 'patches', projName, candidate_size=100, evenly=False)
                
                # patchDirPath = dir / 'patches'
                # fixedMidList, correctPatchList = checkCorrectFix(projPath, patchDirPath)
                # correctPatches.extend(correctPatchList)
                # fixedDict[projName] = fixedMidList
                # for mid in fixedMidList:
                #     mutator = getMutator(projPath, mid)
                #     if mutator not in mutatorDict:
                #         mutatorDict[mutator] = []
                #     mutatorDict[mutator].append(projName + '-' + mid)
    # proj = [k for k in fixedDict]
    # proj.sort()
    # for key in proj:
    #     print("{} mutants of {} are correctly (exactly) fixed!".format(len(fixedDict[key]), key))
    # mutators = [k for k in mutatorDict]
    # mutators.sort()
    # for m in mutators:
    #     print("{}: {}".format(m, mutatorDict[m]))
    # for m in mutators:
    #     print("{}: {}".format(m, len(mutatorDict[m])))

    # print('fixedDict = ' + str(fixedDict))
    # print('correctPatches = ' + str(correctPatches))

def checkCorrectFix(projPath: Path, patchDirPath: Path):
    fixedMidList = set()
    correctPatches = []
    projSrcRelativePath = sp.check_output("defects4j export -p dir.src.classes", shell=True, universal_newlines=True, cwd=str(projPath), stderr=sp.DEVNULL).strip()
    for patchFile in patchDirPath.iterdir():
        if str(patchFile).endswith('.txt'):
            mid = patchFile.stem.split('-')[1]
            pid = patchFile.stem.split('-')[0]
            fixedLine = getMutFixedLine(projPath, mid, projSrcPath=projSrcRelativePath)
            with patchFile.open() as f:
                patchIdx = 1
                for line in f:
                    if isExactlySameCode(line, fixedLine):
                        fixedMidList.add(mid)
                        correctPatches.append('{}-{}-{}'.format(pid, mid, patchIdx))
                    patchIdx += 1
    return list(fixedMidList), correctPatches

def isExactlySameCode(a:str, b:str):
    tmp1 = ''.join(a.split())
    tmp2 = ''.join(b.split())
    return tmp1 == tmp2

def recoverPatches(projPath: Path, rankedJsonPath: Path, outputDir: Path, projName: str, candidate_size=-1, evenly=False, overwrite=False):
    outputDir.mkdir(exist_ok=True, parents=True)
    tmp_dir = str(projPath) + '/'
    reranked_result_path = str(rankedJsonPath)

    with rankedJsonPath.open() as f:
        reranked_result = json.load(f)
        for key in reranked_result:
            
            proj, bug_id, path, start_loc, end_loc = key.split('-')

            targetFile = outputDir / (projName + '-' + bug_id + '.txt')
            # Override mode!
            if targetFile.exists():
                targetFile.unlink(missing_ok=True)

            print('===== Recovering Mutant-{} ====='.format(bug_id))
            
            # bug_start_time = time.time()
            i = 0
            resPatches = []
            for tokenized_patch in reranked_result[key]['patches']:
                # print('***** Patch-{} *****'.format(i))
                i += 1
                score = tokenized_patch['score']
                tokenized_patch = tokenized_patch['patch']
                # print("tokenized_patch: '{}'".format(str(tokenized_patch)))

                strings, numbers = get_strings_numbers(tmp_dir + path, (int(start_loc) + int(end_loc)) // 2)
                strings = [item[0] for item in strings][:5]
                numbers = [item[0] for item in numbers][:5]
                # print('strings: ' + str(strings))
                # print('numbers: ' + str(numbers))
                # one tokenized patch may be reconstructed to multiple source-code patches
                reconstructed_patches = tokenization.token2statement(tokenized_patch.split(' '), numbers, strings)
                reconstructed_patches = reconstructed_patches[:5]  # originally they use reconstructed_patches[:5]
                # print('reconstructed_patches: ' + str(reconstructed_patches))
                # validate most 5 source-code patches come from the same tokenized patch

                # force the number of output patches to be candidate_size, candidate_size=-1 means no number limitation
                if candidate_size > 0:
                    # each abstract patch only generate one concrete patch
                    if evenly:
                        resPatches.append(reconstructed_patches[0])
                    # each abstract patch can generate several concrete patch, use top-candi_size of the concrete patches
                    else:
                        if len(resPatches) >= candidate_size:
                            break
                        elif len(resPatches) + len(reconstructed_patches) <= candidate_size:
                            resPatches.extend(reconstructed_patches)
                        else:
                            candLeft = candidate_size - len(resPatches)
                            resPatches.extend(reconstructed_patches[:candLeft])
                            break
                else:
                    resPatches.extend(reconstructed_patches)
            with targetFile.open(mode='a') as t:
                for patch in resPatches:
                    patch = patch.strip()
                    t.write(patch + '\n')

def recoverPatchesD4j(rankedJsonPath: Path, outputDir: Path, candidate_size=-1, evenly=False):
    outputDir.mkdir(exist_ok=True, parents=True)
    reranked_result_path = str(rankedJsonPath)

    with rankedJsonPath.open() as f:
        reranked_result = json.load(f)
        for key in reranked_result:
            
            proj, bug_id, path, start_loc, end_loc = key.split('-')

            targetFile = outputDir / (proj + '-' + bug_id + '.txt')
            # Override mode!
            if targetFile.exists():
                targetFile.unlink(missing_ok=True)

            print('===== Recovering {}-{} ====='.format(proj, bug_id))
            
            # bug_start_time = time.time()
            i = 0
            resPatches = []
            for tokenized_patch in reranked_result[key]['patches']:
                # print('***** Patch-{} *****'.format(i))
                i += 1
                score = tokenized_patch['score']
                tokenized_patch = tokenized_patch['patch']
                # print("tokenized_patch: '{}'".format(str(tokenized_patch)))

                strings, numbers = get_strings_numbers(path, (int(start_loc) + int(end_loc)) // 2)
                strings = [item[0] for item in strings][:5]
                numbers = [item[0] for item in numbers][:5]
                # print('strings: ' + str(strings))
                # print('numbers: ' + str(numbers))
                # one tokenized patch may be reconstructed to multiple source-code patches
                reconstructed_patches = tokenization.token2statement(tokenized_patch.split(' '), numbers, strings)
                reconstructed_patches = reconstructed_patches[:5]  # originally they use reconstructed_patches[:5]
                # print('reconstructed_patches: ' + str(reconstructed_patches))
                # validate most 5 source-code patches come from the same tokenized patch

                # force the number of output patches to be candidate_size, candidate_size=-1 means no number limitation
                if candidate_size > 0:
                    # each abstract patch only generate one concrete patch
                    if evenly:
                        resPatches.append(reconstructed_patches[0])
                    # each abstract patch can generate several concrete patch, use top-candi_size of the concrete patches
                    else:
                        if len(resPatches) >= candidate_size:
                            break
                        elif len(resPatches) + len(reconstructed_patches) <= candidate_size:
                            resPatches.extend(reconstructed_patches)
                        else:
                            candLeft = candidate_size - len(resPatches)
                            resPatches.extend(reconstructed_patches[:candLeft])
                            break
                else:
                    resPatches.extend(reconstructed_patches)
            with targetFile.open(mode='a') as t:
                for patch in resPatches:
                    patch = patch.strip()
                    t.write(patch + '\n')

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

def doGeneratePatchedJavaFile(originalFile: Path, repalceLineNum: int, replaceContent: str, outputFile: Path):
    with originalFile.open() as f:
        contents = f.readlines()
    contents[repalceLineNum-1] = replaceContent
    outputFile.parent.mkdir(parents=True, exist_ok=True)
    with outputFile.open(mode='w') as f:
        for line in contents:
            f.write(line)

def generatePatchedJavaFile(projPath: Path, mid: str, patchLine: str, outputDir: Path, projSrcPath=None):
    projSrcRelativePath = sp.check_output("defects4j export -p dir.src.classes", shell=True, universal_newlines=True, cwd=str(projPath), stderr=sp.DEVNULL).strip() if projSrcPath is None else projSrcPath
    shortPath = sp.check_output('find . -name "*.java"', shell=True, universal_newlines=True, cwd=str(projPath / 'mutants' / mid)).strip()
    mutLineNum = getMutLineNum(projPath, mid)
    originalJavaFilePath = projPath / 'mutants' / mid / shortPath
    doGeneratePatchedJavaFile(originalJavaFilePath, mutLineNum, patchLine, outputDir / shortPath)

patchedSourceOutputDir = Path('cure_patches').resolve()

src_rela_dir = {"chart": "source", "cli": "src/java", "closure": "src", "codec": "src/java", "collections": "src/main/java", "compress": "src/main/java", "csv": "src/main/java",\
        "gson": "gson/src/main/java", "jacksoncore": "src/main/java", "jacksondatabind": "src/main/java", "jacksonxml": "src/main/java", \
        "jsoup": "src/main/java", "jxpath": "src/java", "lang": "src/main/java", "math": "src/main/java", "mockito": "src", "time": "src/main/java"}

def collectSourcePatches():
    for resultDir in mutResultDirPath.iterdir():
        if not resultDir.is_dir():
            continue
        projName = resultDir.name.split('-')[0]
        for projPath in d4jProjPaths:
            if projName == projPath.stem.split('-')[0]:
                projSrcRelativePath = src_rela_dir[projName]
                patchDir = resultDir / 'patches'
                assert patchDir.exists()
                for patchFile in patchDir.iterdir():
                    name, mid = patchFile.stem.split('-')
                    assert name == projName
                    with patchFile.open() as f:
                        patchId = 1
                        for patchLine in f:
                            print('Processing {}-{}-{}'.format(projName, mid, patchId))
                            targetOutputDir = patchedSourceOutputDir / (projName + '_' + mid) / 'patches-pool' / str(patchId)
                            generatePatchedJavaFile(projPath, mid, patchLine, targetOutputDir, projSrcPath=projSrcRelativePath)
                            patchId += 1


# for path in d4jProjPaths:
#     print(str(path))
#     print(getSampledMutIdList(path))
#     print(len(getSampledMutIdList(path)))

if __name__ == '__main__':
    os.environ['CUDA_VISIBLE_DEVICES'] = "0"
    os.environ['CUDA_LAUNCH_BLOCKING'] = "1"

    if len(sys.argv) != 2:
        print('Usage: python3 {} <command>'.format(sys.argv[0]))
        print('command: mutationBenchmark, mutatorDistribution')
        exit(1)
    command = sys.argv[1]
    if command == 'mutationBenchmark':
        mutResultDirPath = TESTER_DIR_PATH / 'predictionResult-mutationBenchmark'
        mutResultDirPath = mutResultDirPath.resolve()
        main(generateForNewSample=False)
        recoverPatches()
        collectSourcePatches()
    elif command == 'mutatorDistribution':
        mutResultDirPath = TESTER_DIR_PATH / 'predictionResult-mutatorDistribution'
        mutResultDirPath = mutResultDirPath.resolve()
        main(generateForNewSample=True)
        recoverPatches()
        collectSourcePatches()
