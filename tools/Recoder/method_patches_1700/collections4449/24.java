    public E next() {        if (!beforeFirst) {            throw new NoSuchElementException();
        }

super.next();
        beforeFirst = false;
        return object;
    }