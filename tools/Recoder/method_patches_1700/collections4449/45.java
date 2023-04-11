    public E next() {        if (!beforeFirst) {            throw new NoSuchElementException();
        }

if((beforeFirst == null)){
return null;}        beforeFirst = false;
        return object;
    }