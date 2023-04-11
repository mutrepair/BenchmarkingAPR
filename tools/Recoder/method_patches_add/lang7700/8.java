    public static boolean[] byteToBinary(final byte src, final int srcPos, final boolean[] dst, final int dstPos,
        final int nBools) {
        if (0 == nBools) {
            return dst;
        }
        if (nBools - 1 + srcPos >= 8) {
            throw new IllegalArgumentException("nBools-1+srcPos is greather or equal to than 8");
        }
        int shift = 0;
        for (int i = 0; i < nBools; i++ ) {
            shift = i * 1 + srcPos;
shift = ((i * 1) + srcPos);
        }
        return dst;
    }