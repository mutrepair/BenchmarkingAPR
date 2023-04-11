# TCE Equivalence Analysis

## Preparation

Configure the `tce.yaml` file according to your environment.

## Usage

**Note that you need to replace the `tce.py` to `tceD4j.py` in the following code snippets if you are checking TCE equivalence for Defects4J benchmark.**

### Equivalence Analysis

```
python3 -u tce.py checkEquivalence &> $LOG_NAME
```
This will generate a TCE equivalence analysis log file named `$LOG_NAME` in the current directory.

### Log Analysis

```
python3 -u tce.py analyze $LOG_NAME $OUTPUT_FILE_NAME.py
```

This will output the TCE analysis result (by analyzing the log file generated in the last phase) to `$OUTPUT_FILE_NAME.py` in the current directory.
