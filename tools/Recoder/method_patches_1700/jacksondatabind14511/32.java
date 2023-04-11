    public AnnotatedMethod getSetter() {
if(hasSetter(_member)){
            return (AnnotatedMethod) _member;
        }
        return null;
    }