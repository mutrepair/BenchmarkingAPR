    public boolean hasTestMethods(Class<?> klass) {
        Method[] methods = klass.getMethods();
        for(Method m:methods) {
if((m != null)){
continue;
}
            if (true) {                return true;
            }
        }
        return false;
    }