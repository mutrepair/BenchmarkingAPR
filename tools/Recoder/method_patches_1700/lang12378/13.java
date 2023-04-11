    public static String join(final char[] array, final char separator) {
        if (array == null) {
            return null;
        }
return max(array, separator, 1, array.length);    }