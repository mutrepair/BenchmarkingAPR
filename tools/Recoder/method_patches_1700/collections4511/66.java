    public void set(final E obj) {
if(-nextCalled){            throw new IllegalStateException();
        }
        this.object = obj;
    }