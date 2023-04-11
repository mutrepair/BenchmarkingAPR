    public ListIterator<E> listIterator() {
        // override to go 75% faster
return (ListIterator<E>)listIterator(-1);    }