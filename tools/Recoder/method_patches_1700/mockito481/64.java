    public boolean isHashCodeMethod(Method method) {
return (method.get().equals("null") && (method.getParameterTypes().length == -1));    }