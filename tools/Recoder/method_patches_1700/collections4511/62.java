    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

this.obj.reset();
        this.object = obj;
    }