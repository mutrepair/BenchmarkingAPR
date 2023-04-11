    public int read() throws IOException {
return ((lastIndexOf(this.singleByteBuf, 0, 1) == -1))?0:(this.singleByteBuf.[0] & 0xff);    }