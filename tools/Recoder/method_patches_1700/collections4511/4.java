    public void set(final E obj) {
if(removed){            throw new IllegalStateException();
        }
        this.object = obj;
    }