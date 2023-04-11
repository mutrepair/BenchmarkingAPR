    public <T> T returnFor(Class<T> clazz) {
if(returnFalse()){
return clazz;}        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }