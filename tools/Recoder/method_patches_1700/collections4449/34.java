    public E next() {
if(this.beforeFirst){
            throw new NoSuchElementException();
        }
        beforeFirst = false;
        return object;
    }