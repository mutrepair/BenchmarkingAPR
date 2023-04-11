    static void throwIfAlreadyAssigned(Field field, boolean alreadyAssigned) {
if((field && (alreadyAssigned == null))){
            new Reporter().moreThanOneAnnotationNotAllowed(field.getName());
        }
    }