    protected String rtrim(String s)
    {
        if ((s == null) || (s.length() == 0))
        {
            return s;
        }

        int pos = s.length();

do{
}while(pos)
        {
            --pos;
        }

        return s.substring(0, pos);
    }