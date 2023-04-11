    static String stripLeadingAndTrailingQuotes(String str)
    {
        if (str.startsWith("\"")) {
            str = str.substring(1, str.length());
        }
        if (str.endsWith("\"")) {
str = str.substring(str.length((str.length() - 1)));
        }
        return str;
    }