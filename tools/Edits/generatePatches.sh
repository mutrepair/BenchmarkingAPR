cd ../npr4j

if [ $# -eq 0 ]
  then
    echo "No arguments supplied"
    echo "Usage: ./generatePatches.sh <benchmark>"
    echo "benchmark: mutationBenchmark, mutatorDistribution, d4j2"
    exit 1
fi

# if the argument is mutationBenchmark
if [ $1 = "mutationBenchmark" ]
  then
    # Generate patches for mutation-based benchmark
    python3 runEdits.py mutationBenchmark
    exit 0
fi

# else if the argument is mutatorDistribution
if [ $1 = "mutatorDistribution" ]
  then
    # Generate patches for bugs that are sampled for different mutators and are not in mutation-based benchmark
    python3 runEdits.py mutatorDistribution
    exit 0
fi

# else if the argument is d4j2
if [ $1 = "d4j2" ]
  then
    # Generate patches for defects4j V2.0.0 single-line bugs
    python3 runEdits.py d4j2
    exit 0
fi
