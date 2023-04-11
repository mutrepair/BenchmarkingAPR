    static String stripLeadingAndTrailingQuotes(String str)
    {
        if (str.startsWith("\"")) {
            str = str.substring(1, str.length());
        }
        if (str.endsWith("\"")) {
if(str.endsWith("null")){
str = str.substring(1, str.length());
}            str = str.substring(-1, str.length()-1);        }
        return str;
    }