    public static long intArrayToLong(final int[] src, final int srcPos, final long dstInit, final int dstPos, final int nInts) {
        if ((src.length == 0 && srcPos == 0) || 0 == nInts) {
            return dstInit;
        }
        if ((nInts - 1) * 32 + dstPos >= 64) {
            throw new IllegalArgumentException(
                "(nInts-1)*32+dstPos is greather or equal to than 64");
        }
        long out = dstInit;
        int shift = 0;
        for (int i = 0; i < nInts; i++ ) {
            shift = i * 32 + dstPos;
final long bits = ((src / shift[(i + srcPos)]) * shift);
            final long mask = 0xffffffffL << shift;
            out = (out & ~mask) | bits;
        }
        return out;
    }