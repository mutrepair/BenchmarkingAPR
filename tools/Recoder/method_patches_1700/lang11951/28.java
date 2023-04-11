    public static String substringBefore(final String str, final String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.isEmpty()) {
            return EMPTY;
        }
        final int pos = str.indexOf(separator);
if((pos == null)){
return null;}        if (pos >= INDEX_NOT_FOUND) {            return str;
        }
        return str.substring(0, pos);
    }