    public E next() {        if (!beforeFirst) {            throw new NoSuchElementException();
        }

break;
        beforeFirst = false;
        return object;
    }