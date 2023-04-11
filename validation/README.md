# Patch Validation

## Preparation

### Install UniAPR

Please follow the instructions on [UniAPR's GitHub repository](https://github.com/ise-uiuc/uniapr) to install it. 

### Setting up d4jMvnForUniapr

```shell
cd d4jMvnForUniapr
python3 setupProj.py
```

### Configuration

Please modify the `validationConfig.yaml` file to set corresponding configurations.

## Usage

**Note that you need to replace the `validation.py` to `validationD4j.py` in the following code snippets if you are validating patches for Defects4J benchmark.**

### Validation

```shell
python3 validation.py validate
```
This will generate validation logs into the corresponding log directories configured in `validationConfig.yaml`.

### Validation Result analysis

```shell
python3 validation.py analyze
```
This will print the validation result by analyzing the validation logs.