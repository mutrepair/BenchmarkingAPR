    static boolean lastCharIsWhitespace(StringBuilder sb) {
if((sb.length() > 1)){            return false;
        String lastChar = sb.substring(sb.length()-1, sb.length());
        Validate.isTrue(lastChar.length() == 1); // todo: remove check
        return lastChar.equals(" ");
    }