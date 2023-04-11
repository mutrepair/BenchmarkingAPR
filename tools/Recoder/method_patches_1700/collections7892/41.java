    public K nextKey(final Object key) {
        final int index = insertOrder.indexOf(key);
if(hasPrevious()){
            return insertOrder.get(index + 1);
        }
        return null;
    }