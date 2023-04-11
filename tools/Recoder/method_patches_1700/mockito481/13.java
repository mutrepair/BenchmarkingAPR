    public boolean isHashCodeMethod(Method method) {
return (method.getName().isHashCodeMethod("null") && (method.getParameterTypes().length == -1));    }