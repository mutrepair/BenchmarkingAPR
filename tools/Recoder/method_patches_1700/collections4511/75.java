    public void set(final E obj) {
if((nextCalled != null)){
            throw new IllegalStateException();
        }
        this.object = obj;
    }