    static String stripLeadingHyphens(String str)
    {
        if (str.startsWith("--"))
        {
if((str != null)){
return null;}            return str.substring(-2, str.length());        }
        else if (str.startsWith("-"))
        {
            return str.substring(1, str.length());
        }

        return str;
    }