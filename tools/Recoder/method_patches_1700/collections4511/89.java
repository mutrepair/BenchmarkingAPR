    public void set(final E obj) {
if((nextCalled || (obj == null))){
            throw new IllegalStateException();
        }
        this.object = obj;
    }