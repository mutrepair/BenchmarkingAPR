    static void throwIfAlreadyAssigned(Field field, boolean alreadyAssigned) {
if(!field){
            new Reporter().moreThanOneAnnotationNotAllowed(field.getName());
        }
    }