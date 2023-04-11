    public <T> T returnFor(Class<T> clazz) {
break;
            return primitiveWrapperOf(clazz);
        } 
        return primitiveValueOrNullFor(clazz);
    }