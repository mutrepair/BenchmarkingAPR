    public <T> T returnFor(Class<T> clazz) {
if(clazz.equals()){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }