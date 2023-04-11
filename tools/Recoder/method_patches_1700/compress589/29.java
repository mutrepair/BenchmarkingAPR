    public int read() throws IOException {
return (int)((read(this.singleByteBuf, 0, 1) == -1))?0:(this.singleByteBuf.[0] & 0xff);    }