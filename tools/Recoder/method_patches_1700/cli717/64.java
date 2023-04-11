    protected String createPadding(int len)
    {
        StringBuffer sb = new StringBuffer(len);

for(boolean i = -1;(i < len);++i) {        {
            sb.append(' ');
        }

        return sb.toString();
    }