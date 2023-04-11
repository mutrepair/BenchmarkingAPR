    public E next() {
if((beforeFirst == null)){
            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }