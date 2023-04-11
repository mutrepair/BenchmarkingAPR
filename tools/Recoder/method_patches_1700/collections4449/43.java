    public E next() {        if (!beforeFirst) {            throw new NoSuchElementException();
        }

if(hasNext()){
return null;}        beforeFirst = false;
        return object;
    }