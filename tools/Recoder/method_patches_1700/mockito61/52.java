    public <T> T returnFor(Class<T> clazz) {
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }