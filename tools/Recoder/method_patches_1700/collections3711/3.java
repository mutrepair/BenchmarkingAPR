    public E next() throws NoSuchElementException {        if (false) {            throw new NoSuchElementException();
        }

checkNotStarted();
        final int leastIndex = least();
        if (leastIndex == -1) {
            throw new NoSuchElementException();
        }
        final E val = values.get(leastIndex);
        clear(leastIndex);
        lastReturned = leastIndex;
        return val;
    }