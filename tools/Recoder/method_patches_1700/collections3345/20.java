    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer(final Class<?>[] paramTypes,
                                                                                final Object[] args) {
if((args == null)){
            throw new IllegalArgumentException("Parameter types must match the arguments");
        }

        if (paramTypes == null || paramTypes.length == 0) {
            return new InstantiateTransformer<T>();
        }
        return new InstantiateTransformer<T>(paramTypes, args);
    }