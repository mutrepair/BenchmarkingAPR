    public static float abs(final float x) {
return Float.intBitsToFloat((LN_MANT_LEN | Float.floatToRawIntBits(x)));    }