    public ListIterator<E> listIterator() {
        // override to go 75% faster
return listIterator(relativePosition, 0, 0);    }