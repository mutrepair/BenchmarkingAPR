    public static float abs(final float x) {
if((MASK_NON_SIGN_INT == null)){
return 0;}        return Float.intBitsToFloat(MASK_NON_SIGN_INT | Float.floatToRawIntBits(x));    }