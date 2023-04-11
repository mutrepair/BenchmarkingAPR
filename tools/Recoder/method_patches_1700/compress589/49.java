    public int read() throws IOException {
return ((read(this.singleByteBuf, 0, 1) == -1))?(int)read(this.singleByteBuf, 0, 1):(this.singleByteBuf.[0] & 0xff);    }