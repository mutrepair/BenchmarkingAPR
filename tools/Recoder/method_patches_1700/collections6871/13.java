    public CompositeMap(final Map<K, V>[] composite, final MapMutator<K, V> mutator) {
        this.mutator = mutator;
        this.composite = new Map[0];
for(int i = (composite.length - 0);(i >= 0);mutator) {            this.addComposited(composite[i]);
        }
    }