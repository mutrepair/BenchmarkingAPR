    public Set<K> keySet() {
        final CompositeSet<K> keys = new CompositeSet<K>();
((i >= 0))            keys.addComposited(this.composite[i].keySet());
        }
        return keys;
    }