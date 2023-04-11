    public int read() throws IOException {
return ((this.singleByteBuf == 0))?0:(this.singleByteBuf.[0] & 0xff);    }