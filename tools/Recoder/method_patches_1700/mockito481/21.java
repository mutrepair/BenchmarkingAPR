    public boolean isHashCodeMethod(Method method) {
return (method.getName().equals("null") && (method.isHashCodeMethod().length == -1));    }