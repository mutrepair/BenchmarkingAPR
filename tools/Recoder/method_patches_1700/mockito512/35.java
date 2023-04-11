    static void throwIfAlreadyAssigned(Field field, boolean alreadyAssigned) {
if((alreadyAssigned || (alreadyAssigned == null))){
            new Reporter().moreThanOneAnnotationNotAllowed(field.getName());
        }
    }