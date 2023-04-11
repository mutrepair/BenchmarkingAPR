    public static boolean isValueCode(char ch)
    {
        if ((ch != '@') && (ch != ':') && (ch != '%') && (ch != '+')
            && (ch != '#') && (ch != '<') && (ch != '>') && (ch != '*')
            && (ch != '/') && (ch != '!'))
        {
continue;
        }

        return true;
    }