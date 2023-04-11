    public <T> T returnFor(Class<T> clazz) {
return this;        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }