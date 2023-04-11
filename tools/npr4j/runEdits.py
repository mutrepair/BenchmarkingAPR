
from Preprocess_RawData import preprocess_SequenceR_fromRaw
from Preprocess_RawData import Preprocess_PatchEdits_fromSequenceR
from pathlib import Path
import sys
from translate import translate_PatchEdits
from checkEditsPatches import *


# """
# ids_f: a list of bug-fix ids
# input_dir: raw data dir 
# output_dir: where you want to output the processed code of SequenceR
# tmp_dir: when building a SequenceR-type context, you need a directory to restore temp files
# """
#preprocess_SequenceR_fromRaw("/home/zhongwenkang/RawData/Evaluation/Benchmarks/bears.ids.new","/home/zhongwenkang/RawData/Evaluation/Benchmarks",
                             #"/home/zhongwenkang/RawData_Processed/SequenceR/bears","/home/zhongwenkang/RawData_Processed/SequenceR/temp")

dataDirPath = Path('ids_all_info').resolve()

def useSequencerPreprocessData():
    seqrPath = (dataDirPath / 'SequenceR').resolve()
    seqrPath.mkdir(exist_ok=True)
    preprocess_SequenceR_fromRaw(str(dataDirPath / 'all.ids'), str(dataDirPath), str(dataDirPath / 'SequenceR' / 'mutBench'), str(dataDirPath / 'SequenceR' / 'temp'))
    return seqrPath

#Preprocess_PatchEdits_fromSequenceR(r"D:\RawData_Processed\SequenceR\qbs.sids",r"D:\RawData_Processed\SequenceR\qbs.buggy",
                                    #r"D:\RawData_Processed\SequenceR\qbs.fix",r"D:\RawData_Processed\PatchEdits\qbs.data",
                                    #r"D:\RawData_Processed\PatchEdits\qbs.ids")

def preprocessForEditFromSequencerRawData(seqrPath: Path):
    editsPath = (dataDirPath / 'edits').resolve()
    editsPath.mkdir(exist_ok=True)
    Preprocess_PatchEdits_fromSequenceR(str(seqrPath / 'mutBench.sids'), str(seqrPath / 'mutBench.buggy'), str(seqrPath / 'mutBench.fix'), str(editsPath / 'mutBench.data'), str(editsPath / 'mutBench.ids'))

    # editsRepoPath = Path('{1}')
    # shutil.copyfile(str(editsRepoPath / 'data/model/'))
    heldOutFile = editsPath / 'heldout_keys.txt'
    testKeyFile = editsPath / 'test_keys.txt'
    with heldOutFile.open(mode='w'): pass
    with testKeyFile.open(mode='w') as f:
        f.write('test\n')


# # Firstly use the sequencer preprocessing script to preprocess data
# seqrPath = useSequencerPreprocessData()
# # Then preprocess the data preprocessed by sequncer for edit
# preprocessForEditFromSequencerRawData(seqrPath)

# To run inference of Edits, use translate_PatchEdits method in translate.py

def generateConfig(rawDataPath, editsDirPath):
    text="""data_path: {0}/edits/mutBench.data
vocab_path: {1}/data/model/vocabulary
output_path: {0}/edits/mutBench.pred
model_path: {1}/model/models-edits-context
log_path: {0}/edits/mutBench.log
beam_size: 100
""".format(rawDataPath, editsDirPath)
    assert (Path(editsDirPath)/'model').exists(), 'The path "{}" does not exist!'.format(Path(editsDirPath)/'model')
    assert (Path(editsDirPath)/'data').exists(), 'The path "{}" does not exist!'.format(Path(editsDirPath)/'data')
    targetFile = Path('./Config/mutbench/edits.yaml')
    with targetFile.open(mode='w') as f:
        f.write(text)

if __name__ == '__main__':
    # useSequencerPreprocessData()
    # preprocessForEditFromSequencerRawData(seqrPath)

    if len(sys.argv) != 2:
        print('Usage: python3 {} <command>'.format(sys.argv[0]))
        print('command: mutationBenchmark, mutatorDistribution, d4j2')
        exit(1)
    command = sys.argv[1]
    if command == 'mutationBenchmark':
        dataDirPath = Path('ids_all_info-mutationBenchmark').resolve()
        seqrPath = useSequencerPreprocessData()
        preprocessForEditFromSequencerRawData(seqrPath)
        generateConfig(str(dataDirPath), str(Path('../Edits').resolve()))
        configPath = Path('./Config/mutbench/edits.yaml').resolve()
        translate_PatchEdits(str(configPath))
        recoverPatches(dataDirPath, Path('edits_patches-mutationBenchmark'), checkCorrect=False, keepPatchedJavaFile=True, d4jBench=False)

    elif command == 'mutatorDistribution':
        dataDirPath = Path('ids_all_info-mutatorDistribution').resolve()
        seqrPath = useSequencerPreprocessData()
        preprocessForEditFromSequencerRawData(seqrPath)
        generateConfig(str(dataDirPath), str(Path('../Edits').resolve()))
        configPath = Path('./Config/mutbench/edits.yaml').resolve()
        translate_PatchEdits(str(configPath))
        recoverPatches(dataDirPath, Path('edits_patches-mutatorDistribution'), checkCorrect=False, keepPatchedJavaFile=True, d4jBench=False)

    elif command == 'd4j2':
        dataDirPath = Path('ids_all_info-d4j2').resolve()
        seqrPath = useSequencerPreprocessData()
        preprocessForEditFromSequencerRawData(seqrPath)
        generateConfig(str(dataDirPath), str(Path('../Edits').resolve()))
        configPath = Path('./Config/mutbench/edits.yaml').resolve()
        translate_PatchEdits(str(configPath))
        recoverPatches(dataDirPath, Path('edits_patches-d4j2'), checkCorrect=False, keepPatchedJavaFile=True, d4jBench=True)