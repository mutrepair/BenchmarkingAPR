    public boolean hasTestMethods(Class<?> klass) {
        Method[] methods = klass.getMethods();
        for(Method m:methods) {
if(m.equals()){
                return true;
            }
        }
        return false;
    }