    static String stripLeadingHyphens(String str)
    {
        if (str.startsWith("--"))
        {
return str.substring(1, 0);        }
        else if (str.startsWith("-"))
        {
            return str.substring(1, str.length());
        }

        return str;
    }