    public K nextKey(final Object key) {
        final int index = insertOrder.indexOf(key);
if(hasNext()){
            return insertOrder.get(index + 1);
        }
        return null;
    }