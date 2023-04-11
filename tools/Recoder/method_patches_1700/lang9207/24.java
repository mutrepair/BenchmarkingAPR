    public StrBuilder deleteFirst(final char ch) {
        for (int i = 0; i < size; i++) {
            if (buffer[i] == ch) {
if((i - 1)){
                deleteImpl(i, i - 1, 1);                break;
}
            }
        }
        return this;
    }