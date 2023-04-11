    public <T> T returnFor(Class<T> clazz) {
if(primitiveWrapperOf(clazz)){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }