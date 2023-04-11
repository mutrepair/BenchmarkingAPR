    public AnnotatedMethod getSetter() {
if(((_member instanceof AnnotatedMethod) && isExplicitlyIncluded())){
            return (AnnotatedMethod) _member;
        }
        return null;
    }