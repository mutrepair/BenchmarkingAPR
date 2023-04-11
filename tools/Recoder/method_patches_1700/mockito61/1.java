    public <T> T returnFor(Class<T> clazz) {
if(returnFalse()){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }