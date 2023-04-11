    public <T> T returnFor(Class<T> clazz) {
if((clazz == null)){
return false;}        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }