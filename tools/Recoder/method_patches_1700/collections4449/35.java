    public E next() {        if (!beforeFirst) {            throw new NoSuchElementException();
        }

if((beforeFirst != null)){
        beforeFirst = false;
        return object;
}
    }