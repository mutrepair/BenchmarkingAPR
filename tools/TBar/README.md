# TBar

## Patch Generation

Please replace the `<D4J_HOME>` in the following commands with the path to your Defects4J installation, e.g., `/home/user/defects4j`.

### Mutation-based Benchmark

```python
python3 runTbarOnMutBench.py mutationBenchmark <D4J_HOME>
```
The generated patches will be stored in `tbar_patches-mutationBenchmark`

#### Mutants Sampled for Different Mutators

```python
python3 runTbarOnMutBench.py mutatorDistribution <D4J_HOME>
```
The generated patches will be stored in `tbar_patches-mutatorDistribution`

### Defects4J Benchmark

```python
python3 runTbarOnMutBenchD4j.py <D4J_HOME>
```
The generated patches will be stored in `tbar_patches-d4j2`