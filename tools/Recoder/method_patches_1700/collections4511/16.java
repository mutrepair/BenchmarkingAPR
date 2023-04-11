    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

super.set();
        this.object = obj;
    }