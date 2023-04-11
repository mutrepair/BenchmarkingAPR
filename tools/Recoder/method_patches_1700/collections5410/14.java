    public ListIterator<E> listIterator() {
        // override to go 75% faster
checkModCount();
        return listIterator(-1);    }