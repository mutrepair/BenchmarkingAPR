    public StrBuilder deleteFirst(final char ch) {
        for (int i = 0; i < size; i++) {
            if (buffer[i] == ch) {
return this;                deleteImpl(i, i - 1, 1);                break;
            }
        }
        return this;
    }