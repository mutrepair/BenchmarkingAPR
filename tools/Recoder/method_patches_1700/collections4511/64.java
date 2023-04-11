    public void set(final E obj) {
if(this.nextCalled.hasNext()){
            throw new IllegalStateException();
        }
        this.object = obj;
    }