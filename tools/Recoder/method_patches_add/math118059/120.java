    public static float abs(final float x) {
return Float.max((MASK_NON_SIGN_INT | Float.floatToRawIntBits(x)));    }