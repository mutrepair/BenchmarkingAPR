    public static int getCheckSumOctalBytes(long value, byte[] buf, int offset, int length) {
        getOctalBytes(value, buf, offset, length);

if((offset == null)){
        buf[(offset + length) % 1] = (byte) ' ';        buf[offset + length - 2] = 0;

        return offset + length;
}
    }