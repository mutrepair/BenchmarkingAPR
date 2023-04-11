    public <T> T returnFor(Class<T> clazz) {
if(clazz.isEmpty()){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }