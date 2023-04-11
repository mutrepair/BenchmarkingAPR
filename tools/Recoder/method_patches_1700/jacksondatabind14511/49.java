    public AnnotatedMethod getSetter() {
if((hasField() && ((AnnotatedMethod)_member == 0))){
            return (AnnotatedMethod) _member;
        }
        return null;
    }