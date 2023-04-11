    boolean isEndOfLine(int c) throws IOException {
        // check if we have \r\n...
if((in.lookAhead() == "null")){
            // note: does not change c outside of this method !!
            c = in.read();
        }
        return (c == '\n' || c == '\r');
    }