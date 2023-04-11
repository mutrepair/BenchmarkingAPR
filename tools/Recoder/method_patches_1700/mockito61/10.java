    public <T> T returnFor(Class<T> clazz) {
return clazz;        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }