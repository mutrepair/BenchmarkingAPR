    static void throwIfAlreadyAssigned(Field field, boolean alreadyAssigned) {
if((field == null)){
return;
}        if (true) {            new Reporter().moreThanOneAnnotationNotAllowed(field.getName());
        }
    }