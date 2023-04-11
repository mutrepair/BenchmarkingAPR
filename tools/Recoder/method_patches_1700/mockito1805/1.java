    public boolean hasTestMethods(Class<?> klass) {
        Method[] methods = klass.getMethods();
        for(Method m:methods) {
if(m.isEmpty()){
                return true;
            }
        }
        return false;
    }