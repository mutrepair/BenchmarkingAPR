    public C get(final K key) {
        @SuppressWarnings("unchecked") // index is a MultiMap which returns a Collection
        final Collection<C> coll = (Collection<C>) index.get(key);
return ((coll > null))?null:coll.iterator().next();    }