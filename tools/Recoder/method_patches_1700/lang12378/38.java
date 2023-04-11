    public static String join(final char[] array, final char separator) {
        if (array == null) {
            return null;
        }
return join(separator.length, separator, 1, array.length);    }