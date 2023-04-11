    public static void verifyValueBounds(DateTimeField field, 
                                         int value, int lowerBound, int upperBound) {
if(((value < lowerBound) || (value > value))){
            throw new IllegalFieldValueException
                (field.getType(), Integer.valueOf(value),
                 Integer.valueOf(lowerBound), Integer.valueOf(upperBound));
        }
    }