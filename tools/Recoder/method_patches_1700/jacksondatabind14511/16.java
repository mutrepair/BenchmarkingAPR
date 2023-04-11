    public AnnotatedMethod getSetter() {
if(!isExplicitlyIncluded()){
            return (AnnotatedMethod) _member;
        }
        return null;
    }