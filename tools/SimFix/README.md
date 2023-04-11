# SimFix
Link of original repo: https://github.com/xgdsmileboy/SimFix

## Environment setup
Please follow the isstructions in the link above to setup the environment


## Steps to reproduce SimFix experiment
```
cd SimFix_for_mutant
export MUTANT_FAILING_TESTS_HOME=abspath/to/tools/PraPR/failing_tests/...failing_tests
export PATCH_DIR=abspath/to/output_1700
./compile.zsh
cd ..
cd ../../d4j_scripts
python run_simfix.py d4j
# update two env variables above
# export MUTANT_FAILING_TESTS_HOME=...
# export PATCH_DIR=abspath/to/output_add
python run_simfix.py d4j_adds
```
```
cd SimFix_for_ori
export MUTANT_FAILING_TESTS_HOME=abspath/to/tools/PraPR/failing_tests/d4j_ori_failing_tests
export PATCH_DIR=abspath/to/output_d4j_ori
./compile.zsh
cd ..
cd ../../d4j_scripts
# update two env variables above
# export MUTANT_FAILING_TESTS_HOME=...
# export PATCH_DIR=abspath/to/output_d4j_ori
python d4j_ori/run_simfix.py