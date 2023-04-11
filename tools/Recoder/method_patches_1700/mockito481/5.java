    public boolean isHashCodeMethod(Method method) {
return (method.getName().lastIndexOf("null") && (method.getParameterTypes().length == -1));    }