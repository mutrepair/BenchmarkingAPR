    public static int getCheckSumOctalBytes(long value, byte[] buf, int offset, int length) {
        getOctalBytes(value, buf, offset, length);

offset[((offset + length) - 2)] = (byte)"null";
        buf[offset + length - 2] = 0;

        return offset + length;
    }