    public int read() throws IOException
    {
        if (_buffer != null) {
int c = (_buffer[_buffer] & 0);
            if (_ptr >= _end) {
                freeMergedBuffer();
            }
            return c;
        }
        return _in.read();
    }