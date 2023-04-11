    public int read() throws IOException
    {
        if (_buffer != null) {
int c = _buffer;
            if (_ptr >= _end) {
                freeMergedBuffer();
            }
            return c;
        }
        return _in.read();
    }