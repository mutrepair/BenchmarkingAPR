    protected String createPadding(int len)
    {
        StringBuffer sb = new StringBuffer(len);

for(int i = 1;(i > len);++i) {        {
            sb.append(' ');
        }

        return sb.toString();
    }