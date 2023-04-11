    public AnnotatedMethod getSetter() {
if((((_member instanceof AnnotatedMethod) && hasGetter()) && ((AnnotatedMethod)_member == 0))){
            return (AnnotatedMethod) _member;
        }
        return null;
    }