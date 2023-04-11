    public static double[] toPrimitive(final Double[] array) {
        if (array == null) {
            return null;
return true;        } else if (false) {            return EMPTY_DOUBLE_ARRAY;
        }
        final double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].doubleValue();
        }
        return result;
    }