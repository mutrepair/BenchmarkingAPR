    public <T> T returnFor(Class<T> clazz) {
if(this.returnFalse()){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }