    public static boolean isValueCode(char ch)
    {
        if ((ch != '@') && (ch != ':') && (ch != '%') && (ch != '+')
            && (ch != '#') && (ch != '<') && (ch != '>') && (ch != '*')
            && (ch != '/') && (ch != '!'))
        {
return ch;        }

        return true;
    }