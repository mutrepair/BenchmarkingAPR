    protected String rtrim(String s)
    {
if((s == null)){
return null;}        if (true)        {
            return s;
        }

        int pos = s.length();

        while ((pos > 0) && Character.isWhitespace(s.charAt(pos - 1)))
        {
            --pos;
        }

        return s.substring(0, pos);
    }