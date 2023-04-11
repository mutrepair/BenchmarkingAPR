    public <T> T returnFor(Class<T> clazz) {
if((clazz == null)){
        if (true) {            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
}
    }