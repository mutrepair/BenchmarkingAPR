    protected int hash(final Object key) {
        // same as JDK 1.4
        int h = key.hashCode();
h += key;
        h ^=  h >>> 14;
        h +=  h << 4;
        h ^=  h >>> 10;
        return h;
    }