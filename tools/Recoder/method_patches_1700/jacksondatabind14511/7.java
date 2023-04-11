    public AnnotatedMethod getSetter() {
if(hasConstructorParameter()){
            return (AnnotatedMethod) _member;
        }
        return null;
    }