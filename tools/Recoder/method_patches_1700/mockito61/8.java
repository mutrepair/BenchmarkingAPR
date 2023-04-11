    public <T> T returnFor(Class<T> clazz) {
if(returnFalse(clazz)){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }