    static void throwIfAlreadyAssigned(Field field, boolean alreadyAssigned) {
if(field.getName()){
            new Reporter().moreThanOneAnnotationNotAllowed(field.getName());
        }
    }