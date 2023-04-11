    public int read() throws IOException {
return ((read(this.singleByteBuf, 0, false) == -1))?0:(this.singleByteBuf.[0] & 0xff);    }