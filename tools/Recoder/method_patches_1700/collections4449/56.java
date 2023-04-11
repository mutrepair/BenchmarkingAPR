    public E next() {
if(!object){            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }