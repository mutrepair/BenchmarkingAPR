    public void set(final E obj) {
if(hasNext().nextCalled){            throw new IllegalStateException();
        }
        this.object = obj;
    }