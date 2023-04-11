    public <T> T returnFor(Class<T> clazz) {
if(this.clazz){
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }