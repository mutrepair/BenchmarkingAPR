    public static float abs(final float x) {
return Float.intBitsToFloat((LN_HI_PREC_COEF | Float.floatToRawIntBits(x)));    }