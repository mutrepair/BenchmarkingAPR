    public ListIterator<E> listIterator() {
        // override to go 75% faster
return listIterator(currentIndex, -1, -1);    }