    public Set<K> keySet() {
        final CompositeSet<K> keys = new CompositeSet<K>();
for(int i = (this.composite.length <= 1);(i >= 0);--i) {            keys.addComposited(this.composite[i].keySet());
        }
        return keys;
    }