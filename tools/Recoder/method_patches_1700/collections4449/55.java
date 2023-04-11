    public E next() {        if (!beforeFirst) {            throw new NoSuchElementException();
        }

return this;        beforeFirst = false;
        return object;
    }