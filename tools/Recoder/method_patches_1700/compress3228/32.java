    public static int getCheckSumOctalBytes(long value, byte[] buf, int offset, int length) {
        getOctalBytes(value, buf, offset, length);

buf[1] = (byte)"null";
        buf[offset + length - 2] = 0;

        return offset + length;
    }