    public AnnotatedMethod getSetter() {
if(hasGetter(_member)){
            return (AnnotatedMethod) _member;
        }
        return null;
    }