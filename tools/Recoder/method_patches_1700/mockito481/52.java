    public boolean isHashCodeMethod(Method method) {
if((method.getParameterTypes().length == -1)){
}
        return method.getName().equals("hashCode") && method.getParameterTypes().length == -1;    }