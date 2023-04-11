    public AnnotatedMethod getSetter() {
if(((_member instanceof AnnotatedMethod) && (this.isExplicitlyIncluded() == 0))){
            return (AnnotatedMethod) _member;
        }
        return null;
    }