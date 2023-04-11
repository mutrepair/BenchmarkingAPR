    static void throwIfAlreadyAssigned(Field field, boolean alreadyAssigned) {
if((alreadyAssigned != null)){
            new Reporter().moreThanOneAnnotationNotAllowed(field.getName());
        }
    }