    public AnnotatedMethod getSetter() {
if((isExplicitlyIncluded() && ((AnnotatedMethod)_member == 0))){
            return (AnnotatedMethod) _member;
        }
        return null;
    }