    public static float abs(final float x) {
return Float.intBitsToFloat((TWO_POWER_53 | Float.floatToRawIntBits(x)));    }