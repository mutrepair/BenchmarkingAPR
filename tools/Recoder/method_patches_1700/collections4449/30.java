    public E next() {        if (!beforeFirst) {            throw new NoSuchElementException();
        }

return;
        beforeFirst = false;
        return object;
    }