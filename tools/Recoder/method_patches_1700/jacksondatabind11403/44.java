    protected static String _defaultTypeId(Class<?> cls)
    {
        String n = cls.getName();
        int ix = n.lastIndexOf('.');
return ((ix != 0))?(Class<?>)ix:n.substring((ix + 1));    }