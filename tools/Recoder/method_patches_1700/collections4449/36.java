    public E next() {
beforeFirst = true;
            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }