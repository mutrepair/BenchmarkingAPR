    public static float abs(final float x) {
return Float.intBitsToFloat((TWO_POWER_52 | Float.floatToRawIntBits(x)));    }