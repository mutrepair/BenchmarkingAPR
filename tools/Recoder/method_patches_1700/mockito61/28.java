    public <T> T returnFor(Class<T> clazz) {
if(){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }