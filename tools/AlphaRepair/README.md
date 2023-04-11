# AlphaRepair
Link of original repository: https://zenodo.org/record/6819444#.ZCxWq-zML0o.

## Prepair input for AlphaRepair
AlphaRepair requires an organized directory like:
```
input_for_alpha_repair_example
└── chart_1
    ├── buggy
    │   └── AbstractCategoryItemRenderer.java
    ├── diff_buggy_fixed
    │   └── src.patch
    └── fixed
        └── AbstractCategoryItemRenderer.java
```
src.patch can be obtained by running `diff -u input_for_alpha_repair_example/chart_1/buggy/AbstractCategoryItemRenderer.java input_for_alpha_repair_example/chart_1/fixed/AbstractCategoryItemRenderer.java > input_for_alpha_repair_example/chart_1/diff_buggy_fixed/src.patch`

```
cd d4j_scripts
python make_input_for_alpha_repair.py d4j # for the 1700 bugs in mutation benchmark
python make_input_for_alpha_repair.py d4j_add # for the 300~ bugs for mutator-wise evaluation
cd d4j_ori
python make_input_for_alpha_repair.py d4j_ori # for the 140 bugs from Defects4J benchmark
```

## Patch Prediction
```
cd tools/AlphaRepair/code
# repair.sh is for the main mutation benchmark with 1700 bugs, while repair_add.sh is for the 300~ bugs which can compensate with the mutation benchmark to make up the "mutator-wise" benchmark with 700 bugs. repair_d4j_ori.sh is for the 140 Defects4J bugs.
./repair.sh
./repair_add.sh
./repair_d4j_ori.sh
```

## Post-processing
The output of AlphaRepair is not complete Java programs, run the following script to dump AlphaRepair patched Java files.
```
cd d4j_scripts
python dump_alpha_repair_patches.py d4j # for the 1700 bugs
python dump_alpha_repair_patches.py d4j_add # for the 300 ~ bugs
cd d4j_ori
python dump_alpha_repair_patches.py