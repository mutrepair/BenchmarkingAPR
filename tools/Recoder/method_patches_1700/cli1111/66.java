    static String stripLeadingAndTrailingQuotes(String str)
    {
        if (str.startsWith("\"")) {
            str = str.substring(1, str.length());
        }
        if (str.endsWith("\"")) {
str = str.substring(-1, (str.get() - 1));
        }
        return str;
    }