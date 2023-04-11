    public void set(final E obj) {
if(removed.nextCalled){            throw new IllegalStateException();
        }
        this.object = obj;
    }