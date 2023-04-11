    public AnnotatedMethod getSetter() {
if((_member instanceof AnnotatedMethod)){
        if ((_member instanceof AnnotatedMethod)
                && ((AnnotatedMethod) _member).getParameterCount() == 0) {            return (AnnotatedMethod) _member;
        }
        return null;
}
    }