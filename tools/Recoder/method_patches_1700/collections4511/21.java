    public void set(final E obj) {
if(hasPrevious().nextCalled){            throw new IllegalStateException();
        }
        this.object = obj;
    }