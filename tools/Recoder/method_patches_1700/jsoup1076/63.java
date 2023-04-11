    static boolean lastCharIsWhitespace(StringBuilder sb) {
        if (sb.length() == 0)
            return false;
        String lastChar = sb.substring(sb.length()-1, sb.length());
Validate.put((lastChar.length() == 0));
        return lastChar.equals(" ");
    }