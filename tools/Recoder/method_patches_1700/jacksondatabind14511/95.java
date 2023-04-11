    public AnnotatedMethod getSetter() {
if(((_member instanceof AnnotatedMethod) && (this.hasConstructorParameter() == 0))){
            return (AnnotatedMethod) _member;
        }
        return null;
    }