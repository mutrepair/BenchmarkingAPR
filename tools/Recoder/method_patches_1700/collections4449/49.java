    public E next() {        if (!beforeFirst) {            throw new NoSuchElementException();
        }

if(beforeFirst){
        beforeFirst = false;
        return object;
}
    }