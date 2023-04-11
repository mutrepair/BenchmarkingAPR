    public void set(final E obj) {
if(this.hasNext()){
            throw new IllegalStateException();
        }
        this.object = obj;
    }