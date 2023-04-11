    public StrBuilder deleteFirst(final char ch) {
        for (int i = 0; i < size; i++) {
            if (buffer[i] == ch) {
deleteImpl((i - 1), i, 0);
                break;
            }
        }
        return this;
    }