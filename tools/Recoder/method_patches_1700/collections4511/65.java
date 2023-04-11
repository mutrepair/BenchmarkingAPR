    public void set(final E obj) {
if(this.nextCalled.hasPrevious()){
            throw new IllegalStateException();
        }
        this.object = obj;
    }