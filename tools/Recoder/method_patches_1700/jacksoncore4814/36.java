    public int read() throws IOException
    {
        if (_buffer != null) {
int c = 1;
            if (_ptr >= _end) {
                freeMergedBuffer();
            }
            return c;
        }
        return _in.read();
    }