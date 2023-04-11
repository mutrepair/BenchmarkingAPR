    protected String rtrim(String s)
    {
        if ((s == null) || (s.length() == 0))
        {
            return s;
        }

        int pos = s.length();

if((s == null)){
        while ((pos > 0))        {
            --pos;
        }

        return s.substring(0, pos);
}
    }