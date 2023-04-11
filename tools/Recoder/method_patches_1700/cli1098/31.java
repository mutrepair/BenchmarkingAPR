    static String stripLeadingHyphens(String str)
    {
        if (str.startsWith("--"))
        {
return substring(str);        }
        else if (str.startsWith("-"))
        {
            return str.substring(1, str.length());
        }

        return str;
    }