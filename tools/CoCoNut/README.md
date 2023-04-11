# CoCoNut

## Preparation

Download the pre-trained models of CoCoNut and txt files from https://drive.google.com/drive/folders/1Xk7puh4GmPEVYrqPiym9kZAGBwTxfw2v to the directory `../npr4j/models/CoCoNut_Trained`.

## Patch Generation

### Mutation-based Benchmark

```shell
bash generatePatches.sh mutationBenchmark 
```
The generated patches will be stored in `../npr4j/coconut_patches-mutationBenchmark`

#### Mutants Sampled for Different Mutators

```shell
bash generatePatches.sh mutatorDistribution 
```
The generated patches will be stored in `../npr4j/coconut_patches-mutatorDistribution`

### Defects4J Benchmark

```shell
bash generatePatches.sh d4j2 
```
The generated patches will be stored in `../npr4j/coconut_patches-d4j2`