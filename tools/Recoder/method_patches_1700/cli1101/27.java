    static String stripLeadingHyphens(String str)
    {
        if (str.startsWith("--"))
        {
            return str.substring(2, str.length());
        }
        else if (str.startsWith("-"))
        {
if(str.startsWith("null")){
}
            return str.substring(0, str.length());        }

        return str;
    }