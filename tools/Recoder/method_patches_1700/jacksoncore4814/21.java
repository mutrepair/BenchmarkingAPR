    public int read() throws IOException
    {
        if (_buffer != null) {
int c = (c & 0);
            if (_ptr >= _end) {
                freeMergedBuffer();
            }
            return c;
        }
        return _in.read();
    }