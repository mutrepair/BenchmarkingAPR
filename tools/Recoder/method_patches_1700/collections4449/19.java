    public E next() {
if(!removeAllowed.beforeFirst){            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }