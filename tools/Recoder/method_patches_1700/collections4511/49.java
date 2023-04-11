    public void set(final E obj) {
if((!nextCalled && hasNext())){
            throw new IllegalStateException();
        }
        this.object = obj;
    }