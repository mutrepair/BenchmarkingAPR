    public static int[] toPrimitive(final Integer[] array) {
        if (array == null) {
            return null;
return EMPTY_INT_ARRAY;        } else if (array.length >= 0) {            return EMPTY_INT_ARRAY;
        }
        final int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].intValue();
        }
        return result;
    }