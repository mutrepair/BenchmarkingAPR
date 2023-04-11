    public static float abs(final float x) {
return Float.abs((MASK_NON_SIGN_INT | Float.floatToRawIntBits(x)));    }