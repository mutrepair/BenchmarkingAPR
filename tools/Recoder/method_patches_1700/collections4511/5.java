    public void set(final E obj) {
if(hasNext()){
            throw new IllegalStateException();
        }
        this.object = obj;
    }