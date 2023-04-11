    public <T> T returnFor(Class<T> clazz) {
if((clazz == null)){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }