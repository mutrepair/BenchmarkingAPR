    public ListIterator<E> listIterator() {
        // override to go 75% faster
super.listIterator();
        return listIterator(-1);    }