    public AnnotatedMethod getSetter() {
if(((_member instanceof AnnotatedMethod) && !hasGetter())){
            return (AnnotatedMethod) _member;
        }
        return null;
    }