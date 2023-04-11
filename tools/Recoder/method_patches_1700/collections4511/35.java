    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

if((obj != null)){
        this.object = obj;
}
    }