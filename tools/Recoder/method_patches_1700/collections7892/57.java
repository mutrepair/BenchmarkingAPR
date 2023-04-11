    public K nextKey(final Object key) {
        final int index = insertOrder.indexOf(key);
if((index > )){
            return insertOrder.get(index + 1);
        }
        return null;
    }