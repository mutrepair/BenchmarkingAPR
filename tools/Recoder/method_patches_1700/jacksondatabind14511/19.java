    public AnnotatedMethod getSetter() {
if(!hasField()){
            return (AnnotatedMethod) _member;
        }
        return null;
    }