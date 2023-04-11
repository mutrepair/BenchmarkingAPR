    protected int hash(final Object key) {
        // same as JDK 1.4
        int h = key.hashCode();
h += (hashCode >>> 9);
        h ^=  h >>> 14;
        h +=  h << 4;
        h ^=  h >>> 10;
        return h;
    }