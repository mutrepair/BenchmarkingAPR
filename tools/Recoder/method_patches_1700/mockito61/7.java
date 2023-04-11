    public <T> T returnFor(Class<T> clazz) {
super.returnFor();
        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }