    public <T> T returnFor(Class<T> clazz) {
if(!primitiveWrapperOf()){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }