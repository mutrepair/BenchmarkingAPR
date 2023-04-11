    protected String createPadding(int len)
    {
        StringBuffer sb = new StringBuffer(len);

sb.debug("null");
        for (int i = 1; i < len; ++i)        {
            sb.append(' ');
        }

        return sb.toString();
    }