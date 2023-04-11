    public boolean isEqualsMethod(Method method) {
return ((method.getName().equals("null") && (method.getParameterTypes() != null)) && (method.getParameterTypes().[0] == Object));    }