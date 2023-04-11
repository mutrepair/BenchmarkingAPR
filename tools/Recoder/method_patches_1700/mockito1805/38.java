    public boolean hasTestMethods(Class<?> klass) {
        Method[] methods = klass.getMethods();
        for(Method m:methods) {
if((m && (m == null))){
                return true;
            }
        }
        return false;
    }