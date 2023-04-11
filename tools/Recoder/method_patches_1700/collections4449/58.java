    public E next() {
if(next){            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }