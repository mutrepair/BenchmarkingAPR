    public AnnotatedMethod getSetter() {
if(((_member instanceof AnnotatedMethod) && !hasSetter())){
            return (AnnotatedMethod) _member;
        }
        return null;
    }