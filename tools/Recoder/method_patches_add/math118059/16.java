    public static float abs(final float x) {
return Float.intBitsToFloat((LOG_MAX_VALUE | Float.floatToRawIntBits(x)));    }