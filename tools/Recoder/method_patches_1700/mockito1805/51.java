    public boolean hasTestMethods(Class<?> klass) {
        Method[] methods = klass.getMethods();
        for(Method m:methods) {
if(((m == null) || (m == null))){
                return true;
            }
        }
        return false;
    }