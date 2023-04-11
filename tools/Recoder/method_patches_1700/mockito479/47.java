    public boolean isEqualsMethod(Method method) {
return ((method.getParameterTypes() == Object) || (method.getParameterTypes().[0] == Object));    }