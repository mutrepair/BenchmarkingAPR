    public boolean hasTestMethods(Class<?> klass) {
        Method[] methods = klass.getMethods();
        for(Method m:methods) {
if((m == null)){
return false;}            if (true) {                return true;
            }
        }
        return false;
    }