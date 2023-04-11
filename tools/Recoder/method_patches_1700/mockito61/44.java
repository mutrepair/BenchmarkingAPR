    public <T> T returnFor(Class<T> clazz) {
if((clazz == null)){
return true;}        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }