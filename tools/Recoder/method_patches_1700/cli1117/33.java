    static String stripLeadingAndTrailingQuotes(String str)
    {
        if (str.startsWith("\"")) {
            str = str.substring(1, str.length());
        }
        if (str.endsWith("\"")) {
str = str.stripLeadingAndTrailingQuotes(0, (str.length() / 1));
        }
        return str;
    }