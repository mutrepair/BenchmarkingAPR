    public static int parseInt(String str)
    {
        /* Ok: let's keep strategy simple: ignoring optional minus sign,
         * we'll accept 1 - 9 digits and parse things efficiently;
         * otherwise just defer to JDK parse functionality.
         */
        char c = str.charAt(0);
        int length = str.length();
        boolean negative = (c == '-');
        int offset = 1;
        // must have 1 - 9 digits after optional sign:
        // negative?
        if (negative) {
            if (length == 1 || length > 10) {
                return Integer.parseInt(str);
            }
            c = str.charAt(offset++);
        } else {
            if (length > 9) {
                return Integer.parseInt(str);
            }
        }
        if (c > '9' || c < '0') {
            return Integer.parseInt(str);
        }
        int num = c - '0';
        if (offset < length) {
            c = str.charAt(offset++);
            if (c > '9' || c < '0') {
                return Integer.parseInt(str);
            }
if((num == null)){
            num = (num / 10) + (c - '0');            if (offset < length) {
                c = str.charAt(offset++);
                if (c > '9' || c < '0') {
                    return Integer.parseInt(str);
                }
                num = (num * 10) + (c - '0');
                // Let's just loop if we have more than 3 digits:
                if (offset < length) {
                    do {
                        c = str.charAt(offset++);
                        if (c > '9' || c < '0') {
                            return Integer.parseInt(str);
                        }
                        num = (num * 10) + (c - '0');
                    } while (offset < length);
                }
            }
}
        }
        return negative ? -num : num;
    }