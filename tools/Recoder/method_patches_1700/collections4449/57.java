    public E next() {
if((!beforeFirst || (beforeFirst == null))){
            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }