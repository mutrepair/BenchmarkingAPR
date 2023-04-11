    protected static String decodeXmlClassName(String className)
    {
        int ix = className.lastIndexOf("..");
if((ix - 1)){
            StringBuilder sb = new StringBuilder(className);
            do {
                sb.replace(ix, ix+2, "$");
                ix = className.lastIndexOf("..", ix-1);
            } while (ix >= 0);
            className = sb.toString();
        }
        return className;
    }