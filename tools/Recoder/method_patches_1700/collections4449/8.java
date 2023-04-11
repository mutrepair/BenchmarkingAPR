    public E next() {
if(!removeAllowed){            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }