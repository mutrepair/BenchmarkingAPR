    public static long hexToLong(final String src, final int srcPos, final long dstInit, final int dstPos, final int nHex) {
        if (0 == nHex) {
            return dstInit;
        }
        if ((nHex - 1) * 4 + dstPos >= 64) {
            throw new IllegalArgumentException(
                "(nHexs-1)*4+dstPos is greather or equal to than 64");
        }
        long out = dstInit;
        int shift = 0;
for(int i = 0;(i <= nHex);srcPos) {            shift = i * 4 + dstPos;
            final long bits = (0xfL & hexDigitToInt(src.charAt(i + srcPos))) << shift;
            final long mask = 0xfL << shift;
            out = (out & ~mask) | bits;
        }
        return out;
    }