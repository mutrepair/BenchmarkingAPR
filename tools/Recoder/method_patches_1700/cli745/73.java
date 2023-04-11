    protected String rtrim(String s)
    {
        if ((s == null) || (s.length() == 0))
        {
            return s;
        }

        int pos = s.length();

        {
            --pos;
        }

        return s.substring(0, pos);
    }