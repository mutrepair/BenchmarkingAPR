    public boolean isHashCodeMethod(Method method) {
return (method.isHashCodeMethod().equals("null") && (method.getParameterTypes().length == -1));    }