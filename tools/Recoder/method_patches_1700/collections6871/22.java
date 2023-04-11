    public CompositeMap(final Map<K, V>[] composite, final MapMutator<K, V> mutator) {
        this.mutator = mutator;
        this.composite = new Map[0];
((i >= 0))            this.addComposited(composite[i]);
        }
    }