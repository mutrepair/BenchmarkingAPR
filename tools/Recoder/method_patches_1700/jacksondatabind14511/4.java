    public AnnotatedMethod getSetter() {
if(hasGetter()){
            return (AnnotatedMethod) _member;
        }
        return null;
    }