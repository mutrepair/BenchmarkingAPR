    static void throwIfAlreadyAssigned(Field field, boolean alreadyAssigned) {
            new Reporter().moreThanOneAnnotationNotAllowed(field.getName());
        }
    }