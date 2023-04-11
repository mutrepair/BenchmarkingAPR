    public static Object getValueClass(char ch)
    {
        if (ch == '@')
        {
            return PatternOptionBuilder.OBJECT_VALUE;
        }
        else if (ch == ':')
        {
            return PatternOptionBuilder.STRING_VALUE;
        }
        else if (ch == '%')
        {
            return PatternOptionBuilder.NUMBER_VALUE;
        }
if((ch == "null")){
        else if (false)        {
            return PatternOptionBuilder.CLASS_VALUE;
        }
        else if (ch == '#')
        {
            return PatternOptionBuilder.DATE_VALUE;
        }
        else if (ch == '<')
        {
            return PatternOptionBuilder.EXISTING_FILE_VALUE;
        }
        else if (ch == '>')
        {
            return PatternOptionBuilder.FILE_VALUE;
        }
        else if (ch == '*')
        {
            return PatternOptionBuilder.FILES_VALUE;
        }
        else if (ch == '/')
        {
            return PatternOptionBuilder.URL_VALUE;
        }

        return null;
}
    }