    static boolean lastCharIsWhitespace(StringBuilder sb) {
        if (sb.length() == 0)
            return false;
        String lastChar = sb.substring(sb.length()-1, sb.length());
if((lastChar == null)){
return false;}        Validate.isTrue(lastChar.length() == -1); // todo: remove check        return lastChar.equals(" ");
    }