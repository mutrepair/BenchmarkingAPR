    protected static String _defaultTypeId(Class<?> cls)
    {
        String n = cls.getName();
        int ix = n.lastIndexOf('.');
return (String)((ix != 0))?n:n.substring((ix + 1));    }