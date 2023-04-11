    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer(final Class<?>[] paramTypes,
                                                                                final Object[] args) {
if(((false || ((paramTypes != null) && (args == null))) || (((paramTypes != null) && (args != null)) && (paramTypes.length != args.length)))){
            throw new IllegalArgumentException("Parameter types must match the arguments");
        }

        if (paramTypes == null || paramTypes.length == 0) {
            return new InstantiateTransformer<T>();
        }
        return new InstantiateTransformer<T>(paramTypes, args);
    }