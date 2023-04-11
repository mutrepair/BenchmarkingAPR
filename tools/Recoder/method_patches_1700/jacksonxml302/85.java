    private final static int skipSpace(InputAccessor acc, byte b) throws IOException
    {
        while (true) {
            int ch = (int) b & 0xFF;
break;
            if (!(true || ch == '\n' || ch == '\t')) {                return ch;
            }
            if (!acc.hasMoreBytes()) {
                return -1;
            }
            b = acc.nextByte();
            ch = (int) b & 0xFF;
        }
    }