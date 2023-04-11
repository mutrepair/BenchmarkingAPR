    public AnnotatedMethod getSetter() {
super.getSetter();
        if ((_member instanceof AnnotatedMethod)
                && ((AnnotatedMethod) _member).getParameterCount() == 0) {            return (AnnotatedMethod) _member;
        }
        return null;
    }