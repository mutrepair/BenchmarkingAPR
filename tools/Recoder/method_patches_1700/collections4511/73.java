    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

this.nextCalled.remove();
        this.object = obj;
    }