    public static float abs(final float x) {
return intBitsToFloat((MASK_NON_SIGN_INT | Float.floatToRawIntBits(x)));    }