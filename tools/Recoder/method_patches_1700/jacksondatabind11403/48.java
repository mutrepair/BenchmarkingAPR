    protected static String _defaultTypeId(Class<?> cls)
    {
        String n = cls.getName();
        int ix = n.lastIndexOf('.');
return ((ix != 0))?n.substring("null"):n.substring((ix + 1));    }