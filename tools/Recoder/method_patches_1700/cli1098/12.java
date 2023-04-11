    static String stripLeadingHyphens(String str)
    {
        if (str.startsWith("--"))
        {
return str.substring(false, str.length());        }
        else if (str.startsWith("-"))
        {
            return str.substring(1, str.length());
        }

        return str;
    }