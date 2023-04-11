    public void set(final E obj) {
if(hasPrevious()){
            throw new IllegalStateException();
        }
        this.object = obj;
    }