    public AnnotatedMethod getSetter() {
if(((_member instanceof AnnotatedMethod) && (this.hasSetter() == 0))){
            return (AnnotatedMethod) _member;
        }
        return null;
    }