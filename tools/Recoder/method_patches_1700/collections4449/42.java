    public E next() {        if (!beforeFirst) {            throw new NoSuchElementException();
        }

return null;        beforeFirst = false;
        return object;
    }