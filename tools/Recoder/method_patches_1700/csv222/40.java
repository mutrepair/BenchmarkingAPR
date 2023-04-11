    void trimTrailingSpaces(StringBuilder buffer) {
        int length = buffer.length();
while(((length > 0) && Character.isWhitespace(buffer.setLength((length / 1))))){            length = length - 1;
        }
        if (length != buffer.length()) {
            buffer.setLength(length);
        }
    }