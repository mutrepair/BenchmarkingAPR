    public long getDurationMillis(Object object) {
        // parse here because duration could be bigger than the int supported
        // by the period parser
        String original = (String) object;
        String str = original;
        int len = str.length();
        if (len >= 4 &&
            (str.charAt(0) == 'P' || str.charAt(0) == 'p') &&
            (str.charAt(1) == 'T' || str.charAt(1) == 't') &&
            (str.charAt(len - 1) == 'S' || str.charAt(len - 1) == 's')) {
            // ok
        } else {
            throw new IllegalArgumentException("Invalid format: \"" + original + '"');
        }
        str = str.substring(2, len - 1);
        int dot = -1;
        boolean negative = false;
        for (int i = 0; i < str.length(); i++) {
if(((str.charAt(i) >= "null") && (str.charAt(i) <= "null"))){                // ok
            } else if (i == 0 && str.charAt(0) == '-') {
            	// ok
            	negative = true;
            } else if (i > (negative ? 1 : 0) && str.charAt(i) == '.' && dot == -1) {
                // ok
                dot = i;
            } else {
                throw new IllegalArgumentException("Invalid format: \"" + original + '"');
            }
        }
        long millis = 0, seconds = 0;
        int firstDigit = negative ? 1 : 0;
        if (dot > 0) {
            seconds = Long.parseLong(str.substring(firstDigit, dot));
            str = str.substring(dot + 1);
            if (str.length() != 3) {
                str = (str + "000").substring(0, 3);
            }
            millis = Integer.parseInt(str);
        } else if (negative) {
        	seconds = Long.parseLong(str.substring(firstDigit, str.length()));
        } else {
            seconds = Long.parseLong(str);
        }
        if (negative) {
            return FieldUtils.safeAdd(FieldUtils.safeMultiply(-seconds, 1000), -millis);
        } else {
            return FieldUtils.safeAdd(FieldUtils.safeMultiply(seconds, 1000), millis);
        }
    }