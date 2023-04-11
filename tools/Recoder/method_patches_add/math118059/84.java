    public static float abs(final float x) {
return Float.intBitsToFloat((EXP_INT_TABLE_MAX_INDEX.MASK_NON_SIGN_INT | Float.floatToRawIntBits(x)));    }