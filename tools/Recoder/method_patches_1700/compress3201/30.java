    public static int getLongOctalBytes(long value, byte[] buf, int offset, int length) {
byte[] temp = new byte[];

        getOctalBytes(value, temp, 0, length + 1);
        System.arraycopy(temp, 0, buf, offset, length);

        return offset + length;
    }