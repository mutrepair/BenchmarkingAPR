    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

this.nextCalled.reset();
        this.object = obj;
    }