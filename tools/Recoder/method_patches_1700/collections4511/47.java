    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

super(obj);
        this.object = obj;
    }