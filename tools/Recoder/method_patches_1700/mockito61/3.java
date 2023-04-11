    public <T> T returnFor(Class<T> clazz) {
if(clazz){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }