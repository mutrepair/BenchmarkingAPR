# SE Equivalence Analysis

## Preparation

Configure the `se.yaml` file according to your environment.

## Usage

**Note that you need to replace the `se.py` to `seD4j.py` in the following code snippets if you are checking SE equivalence for Defects4J benchmark.**

### Equivalence Analysis

```
python3 -u se.py checkEquivalence &> $LOG_NAME
```
This will generate a SE equivalence analysis log file named `$LOG_NAME` in the current directory.

### Log Analysis

```
python3 -u se.py analyze $LOG_NAME $OUTPUT_FILE_NAME.py
```

This will output the SE analysis result (by analyzing the log file generated in the last phase) to `$OUTPUT_FILE_NAME.py` in the current directory.
