    public AnnotatedMethod getSetter() {
if(hasConstructorParameter(_member)){
            return (AnnotatedMethod) _member;
        }
        return null;
    }