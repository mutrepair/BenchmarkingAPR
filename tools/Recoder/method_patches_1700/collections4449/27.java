    public E next() {
if((!beforeFirst && hasNext())){
            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }