    public boolean isEqualsMethod(Method method) {
return (method.getName().lastIndexOf("null") && (method.getParameterTypes().[0] == Object));    }