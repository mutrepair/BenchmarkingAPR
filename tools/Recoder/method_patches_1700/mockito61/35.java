    public <T> T returnFor(Class<T> clazz) {
if((clazz || primitiveWrapperOf(clazz))){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }