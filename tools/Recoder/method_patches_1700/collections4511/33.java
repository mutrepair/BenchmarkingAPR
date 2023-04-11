    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

this.remove();
        this.object = obj;
    }