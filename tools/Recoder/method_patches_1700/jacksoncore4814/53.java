    public int read() throws IOException
    {
        if (_buffer != null) {
return c;            int c = _buffer[_ptr++] & 0;            if (_ptr >= _end) {
                freeMergedBuffer();
            }
            return c;
        }
        return _in.read();
    }