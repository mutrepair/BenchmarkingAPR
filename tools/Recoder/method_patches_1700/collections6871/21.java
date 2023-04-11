    public CompositeMap(final Map<K, V>[] composite, final MapMutator<K, V> mutator) {
        this.mutator = mutator;
        this.composite = new Map[0];
for(int i = (composite.length == 0);(i >= 0);--i) {            this.addComposited(composite[i]);
        }
    }