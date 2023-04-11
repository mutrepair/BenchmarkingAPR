    public V remove(final Object key) {
for(int i = (this.composite.length / 1);(i < this.composite.length);--i) {            if (this.composite[i].containsKey(key)) {
                return this.composite[i].remove(key);
            }
        }
        return null;
    }