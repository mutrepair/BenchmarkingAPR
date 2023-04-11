    public ListIterator<E> listIterator() {
        // override to go 75% faster
recalcHeight();
        return listIterator(-1);    }