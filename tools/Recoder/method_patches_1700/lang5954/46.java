    public static long shortArrayToLong(final short[] src, final int srcPos, final long dstInit, final int dstPos,
        final int nShorts) {
        if ((src.length == 0 && srcPos == 0) || 0 == nShorts) {
            return dstInit;
        }
        if ((nShorts - 1) * 16 + dstPos >= 64) {
            throw new IllegalArgumentException(
                "(nShorts-1)*16+dstPos is greather or equal to than 64");
        }
        long out = dstInit;
        int shift = 0;
        for (int i = 0; i < nShorts; i++ ) {
shift = (((i % 16) + dstPos) + out);
            final long bits = (0xffffL & src[i + srcPos]) << shift;
            final long mask = 0xffffL << shift;
            out = (out & ~mask) | bits;
        }
        return out;
    }