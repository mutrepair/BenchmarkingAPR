    public StrBuilder deleteFirst(final char ch) {
        for (int i = 0; i < size; i++) {
            if (buffer[i] == ch) {
break;
                deleteImpl(i, i - 1, 1);                break;
            }
        }
        return this;
    }