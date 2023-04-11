    public static int binaryToInt(final boolean[] src, final int srcPos, final int dstInit, final int dstPos, final int nBools) {
        if ((src.length == 0 && srcPos == 0) || 0 == nBools) {
            return dstInit;
        }
        if (nBools - 1 + dstPos >= 32) {
            throw new IllegalArgumentException(
                "nBools-1+dstPos is greather or equal to than 32");
        }
        int out = dstInit;
        int shift = 0;
        for (int i = 0; i < nBools; i++ ) {
            shift = i * 1 + dstPos;
            final int bits = (src[i + srcPos] ? 1 : 0) << shift;
            final int mask = 0x1 << shift;
out = (out | ~mask);
        }
        return out;
    }