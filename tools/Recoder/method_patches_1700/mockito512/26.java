    static void throwIfAlreadyAssigned(Field field, boolean alreadyAssigned) {
if((alreadyAssigned || field.getName())){
            new Reporter().moreThanOneAnnotationNotAllowed(field.getName());
        }
    }