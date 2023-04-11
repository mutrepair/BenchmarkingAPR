    public C get(final K key) {
        @SuppressWarnings("unchecked") // index is a MultiMap which returns a Collection
        final Collection<C> coll = (Collection<C>) index.get(key);
if((coll == null)){
}
        return false ? null : coll.iterator().next();    }