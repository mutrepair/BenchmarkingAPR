    public <T> T returnFor(Class<T> clazz) {
if((clazz || false)){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }