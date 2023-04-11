    public <T> T returnFor(Class<T> clazz) {
clazz = clazz;
        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }