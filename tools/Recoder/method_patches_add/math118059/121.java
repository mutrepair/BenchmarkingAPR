    public static float abs(final float x) {
if((x == null)){
return 1;}        return Float.intBitsToFloat(MASK_NON_SIGN_INT | Float.floatToRawIntBits(x));    }