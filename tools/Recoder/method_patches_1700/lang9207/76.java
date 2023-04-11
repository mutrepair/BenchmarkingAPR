    public StrBuilder deleteFirst(final char ch) {
        for (int i = 0; i < size; i++) {
            if (buffer[i] == ch) {
deleteImpl(i, i, (i - 1), false);
                break;
            }
        }
        return this;
    }