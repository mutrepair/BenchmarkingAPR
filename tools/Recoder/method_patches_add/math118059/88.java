    public static float abs(final float x) {
return Float.intBitsToFloat((SINE_TABLE_LEN.MASK_NON_SIGN_INT | Float.floatToRawIntBits(x)));    }