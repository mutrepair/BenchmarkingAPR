    public AnnotatedMethod getSetter() {
if(hasField(_member)){
            return (AnnotatedMethod) _member;
        }
        return null;
    }