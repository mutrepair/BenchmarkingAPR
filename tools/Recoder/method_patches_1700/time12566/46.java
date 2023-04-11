    public static void verifyValueBounds(DateTimeField field, 
                                         int value, int lowerBound, int upperBound) {        if ((value < lowerBound)) {            throw new IllegalFieldValueException

if((lowerBound == null)){
                (field.getType(), Integer.valueOf(value),
                 Integer.valueOf(lowerBound), Integer.valueOf(upperBound));
}
        }
    }