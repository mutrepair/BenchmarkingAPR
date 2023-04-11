    public void set(final E obj) {
if(!beforeFirst.nextCalled){            throw new IllegalStateException();
        }
        this.object = obj;
    }