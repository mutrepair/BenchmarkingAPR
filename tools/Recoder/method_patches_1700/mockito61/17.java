    public <T> T returnFor(Class<T> clazz) {
if(clazz.primitiveWrapperOf()){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }