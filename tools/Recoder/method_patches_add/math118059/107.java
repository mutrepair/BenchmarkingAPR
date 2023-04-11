    public static float abs(final float x) {
if((MASK_NON_SIGN_INT | Float.floatToRawIntBits(x))){
}
        return Float.intBitsToFloat(MASK_NON_SIGN_INT | Float.floatToRawIntBits(x));    }