    public E next() {
if(!hasNext()){
            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }