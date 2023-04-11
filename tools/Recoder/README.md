# Recoder
Link of original repository: git@github.com:pkuzqh/Recoder.git

## Environment setup
We directly reuse NPR4J framework for more convenient reproduction: https://github.com/kwz219/NPR4J, see paper: https://dl.acm.org/doi/abs/10.1145/3551349.3556943. Please configure the environment as stated in https://github.com/pkuzqh/Recoder#dependency. (It may not be able to work in wrong pytorch version.)

## Steps to reproduce Recoder experiments
### Preprocessing
```
cd ../npr4j
python Preprocess_RawData.py all.ids ids_all_info-d4j ../Recoder/processed_d4j_ori # for d4j bugs
python Preprocess_RawData.py all.ids ids_all_info-mutationBenchmark ../Recoder/processed_1700 # for mutation benchmark with 1700 bugs
python Preprocess_RawData.py all.ids ids_all_info-mutatorDistribution ../Recoder/processed_add # for 300~ bugs
```
### Prediction
```
python predict.py -model Recoder -config $CONFIG_PATH # select one from d4j_scripts/config/recoder
```
### Dump patch
```
python print_patches.py output_${mode} method_patches_${mode} # mode can be 1700, add or d4j_ori
cd ../../d4j_scripts
python dump_recoder_patches.py ${mode} # mode can be d4j or d4j_add
python d4j_ori/dump_recoder_patches.py
```