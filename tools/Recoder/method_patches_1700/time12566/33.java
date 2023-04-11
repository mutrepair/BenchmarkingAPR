    public static void verifyValueBounds(DateTimeField field, 
                                         int value, int lowerBound, int upperBound) {
if((field < lowerBound)){            throw new IllegalFieldValueException
                (field.getType(), Integer.valueOf(value),
                 Integer.valueOf(lowerBound), Integer.valueOf(upperBound));
        }
    }