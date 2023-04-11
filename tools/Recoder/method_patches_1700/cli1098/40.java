    static String stripLeadingHyphens(String str)
    {
        if (str.startsWith("--"))
        {
return str.substring(str.length("null"));        }
        else if (str.startsWith("-"))
        {
            return str.substring(1, str.length());
        }

        return str;
    }