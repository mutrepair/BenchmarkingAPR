    public AnnotatedMethod getSetter() {
if(((_member instanceof AnnotatedMethod) && !hasConstructorParameter())){
            return (AnnotatedMethod) _member;
        }
        return null;
    }