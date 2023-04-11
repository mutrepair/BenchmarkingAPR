    protected String createPadding(int len)
    {
        StringBuffer sb = new StringBuffer(len);

sb++;
        for (int i = 1; i < len; ++i)        {
            sb.append(' ');
        }

        return sb.toString();
    }