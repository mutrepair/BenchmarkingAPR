    public void set(final E obj) {
if(obj.nextCalled){            throw new IllegalStateException();
        }
        this.object = obj;
    }