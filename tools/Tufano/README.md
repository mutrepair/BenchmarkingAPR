# Tufano

## Preparation

Download the pre-trained models of Tufano from https://zenodo.org/record/7819697#.ZDXTuuyZPdo. Extract the zip file, and move all the files in the `model` folder to the directory `model`.

## Patch Generation

### Mutation-based Benchmark

```shell
bash generatePatches.sh mutationBenchmark 
```
The generated patches will be stored in `tufano_patches-mutationBenchmark`

#### Mutants Sampled for Different Mutators

```shell
bash generatePatches.sh mutatorDistribution 
```
The generated patches will be stored in `tufano_patches-mutatorDistribution`

### Defects4J Benchmark

```shell
bash generatePatches.sh d4j2 
```
The generated patches will be stored in `tufano_patches-d4j2`