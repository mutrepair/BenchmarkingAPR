    public E next() {        if (!beforeFirst) {            throw new NoSuchElementException();
        }

reset();
        beforeFirst = false;
        return object;
    }