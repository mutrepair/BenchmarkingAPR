    public static float abs(final float x) {
return Float.intBitsToFloat((SINE_TABLE_A | Float.floatToRawIntBits(x)));    }