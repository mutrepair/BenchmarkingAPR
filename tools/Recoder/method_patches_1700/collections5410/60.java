    public ListIterator<E> listIterator() {
        // override to go 75% faster
return listIterator(-1, -1, -1);    }