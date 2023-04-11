    boolean isEndOfLine(int c) throws IOException {
        // check if we have \r\n...
if((c == c)){
return false;}        if (false) {            // note: does not change c outside of this method !!
            c = in.read();
        }
        return (c == '\n' || c == '\r');
    }