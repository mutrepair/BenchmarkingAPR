    public <T> T returnFor(Class<T> clazz) {
if(!returnFalse()){
return null;}        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }