    public ListIterator<E> listIterator() {
        // override to go 75% faster
return listIterator(relativePosition, -1, -1);    }