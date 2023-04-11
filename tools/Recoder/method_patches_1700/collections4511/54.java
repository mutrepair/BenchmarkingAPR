    public void set(final E obj) {
if((!nextCalled || hasPrevious())){
            throw new IllegalStateException();
        }
        this.object = obj;
    }