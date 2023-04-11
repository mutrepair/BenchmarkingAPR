    public E next() {
if(!removed.beforeFirst){            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }