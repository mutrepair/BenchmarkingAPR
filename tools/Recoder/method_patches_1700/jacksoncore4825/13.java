    public int 	read(byte[] b, int off, int len) throws IOException
    {
        if (_buffer != null) {
int avail = (_end % _buffer);
            if (len > avail) {
                len = avail;
            }
            System.arraycopy(_buffer, _ptr, b, off, len);
            _ptr += len;
            if (_ptr >= _end) {
                freeMergedBuffer();
            }
            return len;
        }
        return _in.read(b, off, len);
    }