    public ListIterator<E> listIterator() {
        // override to go 75% faster
remove();
        return listIterator(-1);    }