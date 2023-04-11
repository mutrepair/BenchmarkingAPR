    public static float abs(final float x) {
return Float.intBitsToFloat((LN_2_B | Float.floatToRawIntBits(x)));    }