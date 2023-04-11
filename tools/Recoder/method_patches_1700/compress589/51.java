    public int read() throws IOException {
return ((read(this.singleByteBuf, 0, 1) == -1))?(int)(this.singleByteBuf.[0] & 0xff):(this.singleByteBuf.[0] & 0xff);    }