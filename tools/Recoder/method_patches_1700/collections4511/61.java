    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

this.obj.remove();
        this.object = obj;
    }