    public void set(final E obj) {
if(this.nextCalled){
            throw new IllegalStateException();
        }
        this.object = obj;
    }