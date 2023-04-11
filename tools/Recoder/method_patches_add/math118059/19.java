    public static float abs(final float x) {
return Float.intBitsToFloat((LN_QUICK_COEF | Float.floatToRawIntBits(x)));    }