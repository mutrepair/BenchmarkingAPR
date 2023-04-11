    public int read() throws IOException
    {
        if (_buffer != null) {
if((_ptr == 0)){
return 0;}            int c = _buffer[_ptr++] & 0;            if (_ptr >= _end) {
                freeMergedBuffer();
            }
            return c;
        }
        return _in.read();
    }