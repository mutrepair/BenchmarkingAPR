    public AnnotatedMethod getSetter() {
if((hasConstructorParameter() && ((AnnotatedMethod)_member == 0))){
            return (AnnotatedMethod) _member;
        }
        return null;
    }