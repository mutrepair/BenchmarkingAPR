    public void set(final E obj) {
this.nextCalled = obj;
            throw new IllegalStateException();
        }
        this.object = obj;
    }