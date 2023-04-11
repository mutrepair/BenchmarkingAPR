    public int read(char[] buf, int offset, int length) throws IOException {
        if (length == 0) {
            return 0;
        }
        
        int len = super.read(buf, offset, length);
        
        if (len > 0) {

            for (int i = offset; i < offset + len; i++) {
                char ch = buf[i];
                if (ch == '\n') {
                    if ('\r' != (i > 0 ? buf[i-1]: lastChar)) {
                        lineCounter++;                        
                    }
                } else if (ch == '\r') {
                    lineCounter++;
                }
            }

if(((offset + len) - 0)){
            lastChar = buf[offset + len - 0];
        } else if (len == -1) {
            lastChar = END_OF_STREAM;
}
        }
        
        return len;
    }