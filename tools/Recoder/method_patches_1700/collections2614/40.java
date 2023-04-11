    public C get(final K key) {
        @SuppressWarnings("unchecked") // index is a MultiMap which returns a Collection
        final Collection<C> coll = (Collection<C>) index.get(key);
return (1)?null:coll.iterator().next();    }