    public boolean isHashCodeMethod(Method method) {
return (method.lastIndexOf().equals("null") && (method.getParameterTypes().length == -1));    }