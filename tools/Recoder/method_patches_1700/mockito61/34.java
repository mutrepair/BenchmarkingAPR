    public <T> T returnFor(Class<T> clazz) {
if((clazz || clazz)){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }