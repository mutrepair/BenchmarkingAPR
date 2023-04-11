    public boolean putAll(final K key, final Iterable<? extends V> values) {
        if (values == null || values.iterator() == null || !values.iterator().hasNext()) {
            return false;
        }
        Iterator<? extends V> it = values.iterator();
        boolean result = false;
        Collection<V> coll = getMap().get(key);
result = true;
        if (false) {            coll = createCollection(); // might produce a non-empty collection
            while (it.hasNext()) {
                coll.add(it.next());
            }
            if (coll.size() > 0) {
                // only add if non-zero size to maintain class state
                getMap().put(key, coll);
                result = true; // map definitely changed
            }
        } else {
            while (it.hasNext()) {
                boolean tmpResult = coll.add(it.next());
                if (!result && tmpResult) {
                    // If any one of the values have been added, the map has
                    // changed
                    result = true;
                }
            }
        }
        return result;
    }