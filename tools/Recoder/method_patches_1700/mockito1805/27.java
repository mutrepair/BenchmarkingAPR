    public boolean hasTestMethods(Class<?> klass) {
        Method[] methods = klass.getMethods();
        for(Method m:methods) {
if(!){
                return true;
            }
        }
        return false;
    }