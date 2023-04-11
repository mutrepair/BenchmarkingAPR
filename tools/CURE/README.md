# CURE: Code-Aware Neural Machine Translation for Automatic Program Repair

## Preparation

Download the pre-trained model of CURE from https://zenodo.org/record/7030145#.ZCzJPOyZPdq and extract the models to the directory `data/models`.

## Patch Generation

### Mutation-based Benchmark

```shell
bash generatePatches.sh mutationBenchmark 
```

#### Mutants Sampled for Different Mutators

```shell
bash generatePatches.sh mutatorDistribution 
```

### Defects4J Benchmark

```shell
bash generatePatches.sh d4j2 
```