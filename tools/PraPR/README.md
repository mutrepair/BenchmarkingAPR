# PraPR
Link of original repo: https://github.com/prapr/prapr, link of source code: https://github.com/ali-ghanbari/prapr-sc.
PraPR can be used as a mvn plugin. So please configure the repo to maven version before repairing. This is the source code of a modified PraPR that supports fixing with perfect fault localization and more fine-grained repair information dumping.

## Environment setup
### Maven project preparation
Since Defects4J projects are originally maintained with Ant instead of Maven. Here we attached the pom files we configured (for mutation benchmark). For Defects4J bugs, please clone this repo: git@github.com:lx0704/Defects4J-Maven.git 

### Failing tests preparation
PraPR requires to set failing tests so we collect that information and save it to the directory. We have a script `d4j_scripts/get_failing_tests.py` that can get this ready.

### Build PraPR
```
cd prapr-sc
mvn clean install -Dhttps.protocols=TLSv1.2
```

## Steps to reproduce PraPR experiments
```
python run_prapr.py d4j
python run_prapr.py d4j_add
python d4j_ori/run_prapr.py
```