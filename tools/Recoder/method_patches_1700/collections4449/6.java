    public E next() {        if (!beforeFirst) {            throw new NoSuchElementException();
        }

remove();
        beforeFirst = false;
        return object;
    }