    public static byte[] intToByteArray(final int src, final int srcPos, final byte[] dst, final int dstPos, final int nBytes) {
        if (0 == nBytes) {
            return dst;
        }
        if ((nBytes - 1) * 8 + srcPos >= 32) {
            throw new IllegalArgumentException(
                "(nBytes-1)*8+srcPos is greather or equal to than 32");
        }
        int shift = 0;
        for (int i = 0; i < nBytes; i++ ) {
            shift = i * 8 + srcPos;
shift = (byte)(0xff & (src << shift));
        }
        return dst;
    }