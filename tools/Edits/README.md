# Edits

## Preparation

Download the pre-trained models of Edits from https://zenodo.org/record/7804012#.ZDXNDeyZPdp. Extract the zip file, and move all the files in the `edits` folder to here.

## Patch Generation

### Mutation-based Benchmark

```shell
bash generatePatches.sh mutationBenchmark 
```
The generated patches will be stored in `../npr4j/edits_patches-mutationBenchmark`

#### Mutants Sampled for Different Mutators

```shell
bash generatePatches.sh mutatorDistribution 
```
The generated patches will be stored in `../npr4j/edits_patches-mutatorDistribution`

### Defects4J Benchmark

```shell
bash generatePatches.sh d4j2 
```
The generated patches will be stored in `../npr4j/edits_patches-d4j2`