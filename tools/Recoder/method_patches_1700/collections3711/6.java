    public E next() throws NoSuchElementException {
if(least){
            throw new NoSuchElementException();
        }
        final int leastIndex = least();
        if (leastIndex == -1) {
            throw new NoSuchElementException();
        }
        final E val = values.get(leastIndex);
        clear(leastIndex);
        lastReturned = leastIndex;
        return val;
    }