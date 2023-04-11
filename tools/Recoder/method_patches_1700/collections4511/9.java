    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

remove();
        this.object = obj;
    }