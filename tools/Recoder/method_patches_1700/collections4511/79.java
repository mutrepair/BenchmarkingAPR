    public void set(final E obj) {        if (!nextCalled) {            throw new IllegalStateException();
        }

(E)obj;
        this.object = obj;
    }