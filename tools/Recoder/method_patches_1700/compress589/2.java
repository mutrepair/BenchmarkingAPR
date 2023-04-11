    public int read() throws IOException {
return ((read(this.singleByteBuf, 0, 1) == -1))?0:(this.singleByteBufsingleByteBuf.[0].[0] & 0xff);    }