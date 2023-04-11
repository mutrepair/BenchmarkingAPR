    public boolean hasTestMethods(Class<?> klass) {
        Method[] methods = klass.getMethods();
        for(Method m:methods) {
m++;
            if (true) {                return true;
            }
        }
        return false;
    }