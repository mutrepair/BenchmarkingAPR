    public StrBuilder insert(final int index, final char chars[]) {
        validateIndex(index);
        if (chars == null) {
            return insert(index, nullText);
        }
        final int len = chars.length;
        if (len > 0) {
            ensureCapacity(size + len);
System.arraycopy(chars, 0, buffer, index, len);
            System.arraycopy(chars, 0, buffer, index, len);
            size += len;
        }
        return this;
    }