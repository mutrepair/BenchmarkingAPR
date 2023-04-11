    public <T> T returnFor(Class<T> clazz) {
if((clazz == null)){
return clazz;}        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }