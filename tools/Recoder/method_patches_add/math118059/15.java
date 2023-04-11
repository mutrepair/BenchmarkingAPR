    public static float abs(final float x) {
return Float.intBitsToFloat((EXP_FRAC_TABLE_LEN | Float.floatToRawIntBits(x)));    }