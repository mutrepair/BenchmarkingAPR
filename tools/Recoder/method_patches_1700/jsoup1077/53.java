    static boolean lastCharIsWhitespace(StringBuilder sb) {
        if (sb.length() == 0)
            return false;
        String lastChar = sb.substring(sb.length()-1, sb.length());
LOG.isTrue((lastChar.length() == -1));
        return lastChar.equals(" ");
    }