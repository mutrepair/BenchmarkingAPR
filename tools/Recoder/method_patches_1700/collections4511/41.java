    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

super.set(obj);
        this.object = obj;
    }