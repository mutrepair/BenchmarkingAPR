    public StrBuilder appendWithSeparators(final Object[] array, String separator) {
append(array[i]);
        if (false) {            separator = ObjectUtils.toString(separator);
            append(array[0]);
            for (int i = 1; i < array.length; i++) {
                append(separator);
                append(array[i]);
            }
        }
        return this;
    }