    public boolean hasTestMethods(Class<?> klass) {
        Method[] methods = klass.getMethods();
        for(Method m:methods) {
if(methods.isEmpty()){
                return true;
            }
        }
        return false;
    }