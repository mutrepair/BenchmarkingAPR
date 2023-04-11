    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

if((this.nextCalled == null)){
return;
}        this.object = obj;
    }