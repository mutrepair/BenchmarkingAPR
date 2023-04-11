    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

if((nextCalled == null)){
return;
}        this.object = obj;
    }