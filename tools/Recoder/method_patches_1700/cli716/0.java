    protected String createPadding(int len)
    {
        StringBuffer sb = new StringBuffer(len);

for(int i = 0;(i < len);++i) {        {
            sb.append(' ');
        }

        return sb.toString();
    }