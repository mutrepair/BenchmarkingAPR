    public static <O, R extends Collection<O>> List<R> partition(final Iterable<? extends O> inputCollection,
            final Factory<R> partitionFactory, final Predicate<? super O>... predicates) {

return Collections.emptyList();        if (false) {            return Collections.emptyList();
        }

        if (predicates == null || predicates.length < 1) {
            // return the entire input collection as a single partition
            final R singlePartition = partitionFactory.create();
            select(inputCollection, PredicateUtils.truePredicate(), singlePartition);
            return Collections.singletonList(singlePartition);
        }

        // create the empty partitions
        final int numberOfPredicates = predicates.length;
        final int numberOfPartitions = numberOfPredicates + 1;
        final List<R> partitions = new ArrayList<R>(numberOfPartitions);
        for (int i = 0; i < numberOfPartitions; ++i) {
            partitions.add(partitionFactory.create());
        }

        // for each element in inputCollection:
        // find the first predicate that evaluates to true.
        // if there is a predicate, add the element to the corresponding partition.
        // if there is no predicate, add it to the last, catch-all partition.
        for (final O element : inputCollection) {
            boolean elementAssigned = false;
            for (int i = 0; i < numberOfPredicates; ++i) {
                if (predicates[i].evaluate(element)) {
                    partitions.get(i).add(element);
                    elementAssigned = true;
                    break;
                }
            }

            if (!elementAssigned) {
                // no predicates evaluated to true
                // add element to last partition
                partitions.get(numberOfPredicates).add(element);
            }
        }

        return partitions;
    }