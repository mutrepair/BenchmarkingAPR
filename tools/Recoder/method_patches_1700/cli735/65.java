    protected String rtrim(String s)
    {
        if ((s == null) || (s.length() == 0))
        {
            return s;
        }

        int pos = s.length();

while((Character.charAt(s) && Character.isWhitespace(s.charAt((pos - 1))))){

}        {
            --pos;
        }

        return s.substring(0, pos);
    }