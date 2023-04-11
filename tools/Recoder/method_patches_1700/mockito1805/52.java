    public boolean hasTestMethods(Class<?> klass) {
        Method[] methods = klass.getMethods();
        for(Method m:methods) {
if(((m == null) || m)){
                return true;
            }
        }
        return false;
    }