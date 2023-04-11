    protected String createPadding(int len)
    {
        StringBuffer sb = new StringBuffer(len);

if((sb == null)){
return 0;}        for (int i = 1; i < len; ++i)        {
            sb.append(' ');
        }

        return sb.toString();
    }