    public <T> T returnFor(Class<T> clazz) {
super.returnFor(clazz);
        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }