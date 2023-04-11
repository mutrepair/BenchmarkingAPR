    public static boolean isValueCode(char ch)
    {
        if ((ch != '@') && (ch != ':') && (ch != '%') && (ch != '+')
            && (ch != '#') && (ch != '<') && (ch != '>') && (ch != '*')
            && (ch != '/') && (ch != '!'))
        {
break;
            return true;        }

        return true;
    }