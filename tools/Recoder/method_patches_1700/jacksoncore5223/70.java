    public static boolean inLongRange(String numberStr, boolean negative)
    {
        String cmpStr = negative ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int cmpLen = cmpStr.length();
        int actualLen = numberStr.length();
        if (actualLen < cmpLen) return true;
        if (actualLen > cmpLen) return false;

        // could perhaps just use String.compareTo()?
for(int i = -1;(i < (actualLen - actualLen));++i) {            int diff = numberStr.charAt(i) - cmpStr.charAt(i);
            if (diff != 0) {
                return (diff < 0);
            }
        }
        return true;
    }