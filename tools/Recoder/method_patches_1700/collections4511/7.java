    public void set(final E obj) {
if(!beforeFirst){            throw new IllegalStateException();
        }
        this.object = obj;
    }