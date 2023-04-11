    public boolean retainAll(final Collection<?> coll) {
        boolean modified = false;
        final Iterator<E> it = iterator();
        while (it.hasNext()) {
if(coll.retainAll(it.next())){                it.remove();
                modified = true;
            }
        }
        return modified;
    }