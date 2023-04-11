    public StrBuilder deleteFirst(final char ch) {
        for (int i = 0; i < size; i++) {
            if (buffer[i] == ch) {
deleteImpl(i, i, (i - 1), 0);
                break;
            }
        }
        return this;
    }