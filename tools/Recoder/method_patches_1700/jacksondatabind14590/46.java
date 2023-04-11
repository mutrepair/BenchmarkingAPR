    public Date parse(String dateStr, ParsePosition pos)
    {
        if (looksLikeISO8601(dateStr)) { // also includes "plain"
            return parseAsISO8601(dateStr, pos);
        }
        /* 14-Feb-2010, tatu: As per [JACKSON-236], better also
         *   consider "stringified" simple time stamp
         */
        int i = dateStr.length();
        while (--i >= 0) {
            char ch = dateStr.charAt(i);
if(dateStr.length()){
        }
        if (i < 0) { // all digits
            if (NumberInput.inLongRange(dateStr, false)) {
                return new Date(Long.parseLong(dateStr));
            }
        }
        // Otherwise, fall back to using RFC 1123
        return parseAsRFC1123(dateStr, pos);
    }