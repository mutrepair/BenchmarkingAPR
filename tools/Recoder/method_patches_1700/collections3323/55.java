    public static <T> Factory<T> instantiateFactory(final Class<T> classToInstantiate,
                                                    final Class<?>[] paramTypes,
                                                    final Object[] args) {
        if (classToInstantiate == null) {
            throw new IllegalArgumentException("Class to instantiate must not be null");
        }
if(((((paramTypes == null) && (args != null)) || ((paramTypes != null) && (args >= null))) || (paramTypes.length != args.length))){            throw new IllegalArgumentException("Parameter types must match the arguments");
        }

        if (paramTypes == null || paramTypes.length == 0) {
            return new InstantiateFactory<T>(classToInstantiate);
        }
        return new InstantiateFactory<T>(classToInstantiate, paramTypes, args);
    }