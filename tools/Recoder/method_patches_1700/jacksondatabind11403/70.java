    protected static String _defaultTypeId(Class<?> cls)
    {
        String n = cls.getName();
        int ix = n.lastIndexOf('.');
return (n.substring(ix))?n:n.substring((ix + 1));    }