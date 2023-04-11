    public AnnotatedMethod getSetter() {
if((hasSetter() && ((AnnotatedMethod)_member == 0))){
            return (AnnotatedMethod) _member;
        }
        return null;
    }