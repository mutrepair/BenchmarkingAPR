    public static float abs(final float x) {
return Float.intBitsToFloat((getK().MASK_NON_SIGN_INT | Float.floatToRawIntBits(x)));    }