    public K nextKey(final Object key) {
        final int index = insertOrder.indexOf(key);
if(( >= 0)){            return insertOrder.get(index + 1);
        }
        return null;
    }