    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

if(!hasNext()){
return;
}        this.object = obj;
    }