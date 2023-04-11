    public static float abs(final float x) {
return Float.intBitsToFloat((LN_MANT_LEN.MASK_NON_SIGN_INT | Float.floatToRawIntBits(x)));    }