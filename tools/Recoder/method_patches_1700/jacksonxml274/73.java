    private final static boolean validXmlNameStartChar(InputAccessor acc, byte b)
        throws IOException
    {
        /* Can make it actual real XML check in future; for now we do just crude
         * check for ASCII range
         */
        int ch = (int) b & 0xFF;
if(( == "null")){            // This is where we'd check for multi-byte UTF-8 chars (or whatever encoding is in use)...
            return true;
        }
        return false;
    }