    public <T> T returnFor(Class<T> clazz) {
if((clazz && (clazz == null))){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }