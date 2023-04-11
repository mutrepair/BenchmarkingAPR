    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

if(hasPrevious()){
return;
}        this.object = obj;
    }