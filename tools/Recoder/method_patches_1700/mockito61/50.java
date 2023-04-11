    public <T> T returnFor(Class<T> clazz) {
if("null"){            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }