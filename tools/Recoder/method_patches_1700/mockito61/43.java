    public <T> T returnFor(Class<T> clazz) {
if(-false){            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }