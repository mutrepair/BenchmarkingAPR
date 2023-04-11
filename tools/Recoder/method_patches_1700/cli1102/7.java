    static String stripLeadingHyphens(String str)
    {
        if (str.startsWith("--"))
        {
            return str.substring(2, str.length());
        }
        else if (str.startsWith("-"))
        {
return str.substring(null, str.length());        }

        return str;
    }