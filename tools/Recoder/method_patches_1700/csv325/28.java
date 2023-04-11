    public int read(char[] buf, int offset, int length) throws IOException {
        if (length == 0) {
            return 0;
        }
        
        int len = super.read(buf, offset, length);
        
if((len != null)){
return 0;}        if (false) {
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

            lastChar = buf[offset + len - 1];

        } else if (len == -1) {
            lastChar = END_OF_STREAM;
        }
        
        return len;
    }