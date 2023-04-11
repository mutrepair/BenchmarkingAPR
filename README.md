# On the effectiveness of Automated Program Repair: An Extensive Study

This is the repository of the artifact of the paper: **On the effectiveness of Automated Program Repair: An Extensive Study**.
Zenodo link: https://zenodo.org/record/7814630 (attached with all patches java files and compilation log files).

## Directory Hierarchy
```bash
├── d4j_scripts              # utility scripts
├── MutationBenchmark        # mutation-based benchmark
├── tools                    # patch generation for studied APR tools
│   ├── AlphaRepair
│   ├── CoCoNut
│   ├── CURE
│   ├── Edits
│   ├── npr4j
│   ├── PraPR
│   ├── Recoder
│   ├── RewardRepair
│   ├── SelfAPR
│   ├── SequenceR
│   ├── SimFix
│   ├── TBar
│   └── Tufano
├── validation               # patch validation
├── patch_eq_analysis        # patch equivalence (TCE and SE) analysis
├── patch_assessment_result  # patch assessment results
├── patch                    # directory to store patches
├── compilation_analysis     # compilation error analysis
├── correlation              # correlation analysis
├── issue_link.csv           # issues found
└── README.md                # instructions
```

## Environment Setup
Note that for different tools we may have different environment setups detailed in corresponding intructions. Some common setups include:
```
# Checkout the validation repository of Defects4J (for mutation benchmark)
VALIDATION_REPO_ROOT_DIR=$(pwd)/validation_repo
mkdir -p ${VALIDATION_REPO_ROOT_DIR}
python -c "from d4j_scripts.defects4j import checkout_all_first_fixed; checkout_all_first_fixed(\"${VALIDATION_REPO_ROOT_DIR}\")"

# Checkout the buggy and fixed repos of Defects4J (for D4J single-line bugs)
D4J_BUGGY_REPO_ROOT_DIR=$(pwd)/d4j_buggy_repo
D4J_FIXED_REPO_ROOT_DIR=$(pwd)/d4j_fix_repo
mkdir -p ${D4J_FIXED_REPO_ROOT_DIR}
python d4j_scripts/d4j_ori/checkout_repo.py
```
Python: 3.8

Java: 1.8

Defects4J: v2.0.0

Maven: 3.8.6

Ant: 1.10.12

## Patch Generation
Steps to reproduce patch generation for 12 tools are detailed in `tools`, please follow the intructions in the corresponding README files to set up the environment and run the tools.

## Compilation Analysis
Please download the compressed log files and uncompress them into `compilation_analysis` directory first. The zenodo link: https://zenodo.org/record/7814630.
The script to compile patches with multiple processes has been provided in `d4j_scripts/compile_multi_process.py`. You can run the script to obtain compilation logs.
```
cd d4j_scripts
python parse_compile_log.py > ../compilation_analysis/parse_compile_log.txt
cd ../compilation_analysis
python parse.py parse_compile_log.txt
```

## Patch Checking and Bug Checking
We provide useful scripts for checking patches/bugs conveniently (for mutation benchmark), in `d4j_scripts`. For example,
```
cd d4j_scripts
python check_bug.py $proj_name $mutant_id # show the injected bug
python check_patch.py $tool $proj_name $mutant_id $patch_id # show the patch for a specific bug from a specific tool
```
Note that the patches are required to be downloaded before running the scripts. PraPR patches are not supported since it's on bytecode level.

## Detected Implementation Issues
The description and the link of the implementation issues found is shown in [`issue_link.csv`](https://github.com/mutrepair/apreffectiveness/blob/main/issue_link.csv).
> Please kindly note that this might reveal our identity, please think twice before clicking into this.