    public static float abs(final float x) {
return Float.intBitsToFloat((x | Float.floatToRawIntBits(x)));    }