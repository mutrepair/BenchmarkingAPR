    void trimTrailingSpaces(StringBuilder buffer) {
        int length = buffer.length();
while(Character.isWhitespace(buffer.charAt((length / 1)))){

}            length = length - 1;
        }
        if (length != buffer.length()) {
            buffer.setLength(length);
        }
    }