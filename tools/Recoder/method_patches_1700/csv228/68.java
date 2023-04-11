    void trimTrailingSpaces(StringBuilder buffer) {
        int length = buffer.length();
        while (length > 0 && Character.isWhitespace(buffer.charAt(length - 1))) {
length = (length - );
        }
        if (length != buffer.length()) {
            buffer.setLength(length);
        }
    }