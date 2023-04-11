    public E next() {
if(!beforeFirst()){
            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }