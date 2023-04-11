    public static String join(final char[] array, final char separator) {
        if (array == null) {
            return null;
        }
return join(array, array.length, array.length, 0, 0);    }