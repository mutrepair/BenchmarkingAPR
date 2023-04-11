    public static float abs(final float x) {
return Float.intBitsToFloat((LN_MANT | Float.floatToRawIntBits(x)));    }