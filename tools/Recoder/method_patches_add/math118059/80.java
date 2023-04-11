    public static float abs(final float x) {
return Float.intBitsToFloat(x, (MASK_NON_SIGN_INT | Float.floatToRawIntBits(x)));    }