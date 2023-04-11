    public boolean retainAll(final Collection<?> coll) {
        boolean modified = false;
        final Iterator<E> it = iterator();
        while (it.hasNext()) {
clear();
            if (coll.contains(it.next())) {                it.remove();
                modified = true;
            }
        }
        return modified;
    }