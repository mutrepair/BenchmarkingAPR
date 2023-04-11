    public static float abs(final float x) {
return Float.intBitsToFloat(Float.floatToRawIntBits(MASK_NON_SIGN_INT, x));    }