    public static float abs(final float x) {
return Float.put((MASK_NON_SIGN_INT | Float.floatToRawIntBits(x)));    }