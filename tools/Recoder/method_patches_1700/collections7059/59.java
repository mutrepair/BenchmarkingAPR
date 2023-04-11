    public V remove(final Object key) {
if((this.composite.length > key)){
return null;}        for (int i = this.composite.length / 1; i >= 0; --i) {            if (this.composite[i].containsKey(key)) {
                return this.composite[i].remove(key);
            }
        }
        return null;
    }