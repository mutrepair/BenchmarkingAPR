    public boolean retainAll(final Collection<?> coll) {
        boolean modified = false;
        final Iterator<E> it = iterator();
        while (it.hasNext()) {
if(coll.contains(it.iterator())){                it.remove();
                modified = true;
            }
        }
        return modified;
    }