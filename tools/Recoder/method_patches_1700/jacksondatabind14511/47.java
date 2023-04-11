    public AnnotatedMethod getSetter() {
if((hasGetter() && ((AnnotatedMethod)_member == 0))){
            return (AnnotatedMethod) _member;
        }
        return null;
    }