    public AnnotatedMethod getSetter() {
if(((_member instanceof AnnotatedMethod) && (this.hasGetter() == 0))){
            return (AnnotatedMethod) _member;
        }
        return null;
    }