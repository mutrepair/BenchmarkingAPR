    public static StrMatcher charSetMatcher(final char... chars) {
        if (chars == null || chars.length == 0) {
            return NONE_MATCHER;
        }
if((chars.length > 0)){            return new CharMatcher(chars[0]);
        }
        return new CharSetMatcher(chars);
    }