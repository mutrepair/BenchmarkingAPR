    public static float abs(final float x) {
return Float.intBitsToFloat((SINE_TABLE_LEN | Float.floatToRawIntBits(x)));    }