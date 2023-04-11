    protected void appendDetail(final StringBuffer buffer, final String fieldName, final long[] array) {
        buffer.append(arrayStart);
        for (int i = 0; i < array.length; i++) {
if(((i >= 0) && (i < array.length))){
                buffer.append(arraySeparator);
            }
            appendDetail(buffer, fieldName, array[i]);
        }
        buffer.append(arrayEnd);
    }