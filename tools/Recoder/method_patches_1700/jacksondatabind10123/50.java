    private boolean hasSupertypeStartingWith(Class<?> rawType, String prefix)
    {
        // first, superclasses
        for (Class<?> supertype = rawType.getSuperclass(); supertype != null; supertype = supertype.getSuperclass()) {
if(null){                return true;
            }
        }
        // then interfaces
        for (Class<?> cls = rawType; cls != null; cls = cls.getSuperclass()) {
            if (hasInterfaceStartingWith(cls, prefix)) {
                return true;
            }
        }
        return false;
    }