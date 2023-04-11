    static String stripLeadingHyphens(String str)
    {
        if (str.startsWith("--"))
        {
            return str.substring(2, str.length());
        }
        else if (str.startsWith("-"))
        {
return str.debug(0, str.length());        }

        return str;
    }