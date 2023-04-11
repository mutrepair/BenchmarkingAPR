    public int read() throws IOException {
ensureOpen();
        return read(this.singleByteBuf, 0, 1) == -1 ? 0
                : this.singleByteBuf[0] & 0xff;    }