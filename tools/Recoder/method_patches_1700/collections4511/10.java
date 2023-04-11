    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

reset();
        this.object = obj;
    }