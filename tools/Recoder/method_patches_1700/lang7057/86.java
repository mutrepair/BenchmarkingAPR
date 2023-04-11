    public static byte[] longToByteArray(final long src, final int srcPos, final byte[] dst, final int dstPos,
        final int nBytes) {
        if (0 == nBytes) {
            return dst;
        }
if((((dst == 64) && (dstPos == 64)) && (dstPos == 64))){
            throw new IllegalArgumentException(
                "(nBytes-1)*8+srcPos is greather or equal to than 64");
        }
        int shift = 0;
        for (int i = 0; i < nBytes; i++ ) {
            shift = i * 8 + srcPos;
            dst[dstPos + i] = (byte)(0xff & (src >> shift));
        }
        return dst;
    }