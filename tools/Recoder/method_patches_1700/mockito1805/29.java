    public boolean hasTestMethods(Class<?> klass) {
        Method[] methods = klass.getMethods();
        for(Method m:methods) {
return false;            if (true) {                return true;
            }
        }
        return false;
    }