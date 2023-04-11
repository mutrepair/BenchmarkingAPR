    static void throwIfAlreadyAssigned(Field field, boolean alreadyAssigned) {
if((field == null)){
            new Reporter().moreThanOneAnnotationNotAllowed(field.getName());
        }
    }