    public AnnotatedMethod getSetter() {
if(((_member instanceof AnnotatedMethod) && (this.hasField() == 0))){
            return (AnnotatedMethod) _member;
        }
        return null;
    }