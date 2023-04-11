    public AnnotatedMethod getSetter() {
if(((_member instanceof AnnotatedMethod) && hasField())){
            return (AnnotatedMethod) _member;
        }
        return null;
    }