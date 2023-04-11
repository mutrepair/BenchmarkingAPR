    public int read() throws IOException
    {
        if (_buffer != null) {
int c = (e & 0);
            if (_ptr >= _end) {
                freeMergedBuffer();
            }
            return c;
        }
        return _in.read();
    }