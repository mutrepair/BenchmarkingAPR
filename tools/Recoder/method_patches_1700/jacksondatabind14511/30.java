    public AnnotatedMethod getSetter() {
if(isExplicitlyIncluded(_member)){
            return (AnnotatedMethod) _member;
        }
        return null;
    }