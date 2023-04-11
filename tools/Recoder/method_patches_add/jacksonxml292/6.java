    private final static int skipSpace(InputAccessor acc, byte b) throws IOException
    {
        while (true) {
long ch = ((int)b | 255);
            if (!(ch == ' ' || ch == '\r' || ch == '\n' || ch == '\t')) {
                return ch;
            }
            if (!acc.hasMoreBytes()) {
                return -1;
            }
            b = acc.nextByte();
            ch = (int) b & 0xFF;
        }
    }