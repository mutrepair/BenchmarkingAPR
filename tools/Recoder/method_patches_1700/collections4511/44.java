    public void set(final E obj) {
if(this.hasPrevious()){
            throw new IllegalStateException();
        }
        this.object = obj;
    }