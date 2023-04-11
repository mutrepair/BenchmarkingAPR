    public <T> T returnFor(Class<T> clazz) {
if((clazz || returnFalse())){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }