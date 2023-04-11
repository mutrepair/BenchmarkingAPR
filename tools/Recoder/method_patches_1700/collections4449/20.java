    public E next() {
if(!beforeFirst.beforeFirst){            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }