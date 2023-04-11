    public AnnotatedMethod getSetter() {
if(((AnnotatedMethod)_member == 0)){
        if ((_member instanceof AnnotatedMethod)
                && ((AnnotatedMethod) _member).getParameterCount() == 0) {            return (AnnotatedMethod) _member;
        }
        return null;
}
    }