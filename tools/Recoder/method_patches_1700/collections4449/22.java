    public E next() {
if(!hasNext().beforeFirst){            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }