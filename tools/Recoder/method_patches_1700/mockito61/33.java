    public <T> T returnFor(Class<T> clazz) {
return;
        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }