    static void throwIfAlreadyAssigned(Field field, boolean alreadyAssigned) {
if(alreadyAssigned.getName()){
            new Reporter().moreThanOneAnnotationNotAllowed(field.getName());
        }
    }