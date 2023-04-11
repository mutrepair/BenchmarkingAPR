    public static float abs(final float x) {
return Float.intBitsToFloat((MASK_NON_SIGN_INT | Float.e(x)));    }