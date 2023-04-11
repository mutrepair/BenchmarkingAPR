    public static float abs(final float x) {
return Float.intBitsToFloat((COSINE_TABLE_A | Float.floatToRawIntBits(x)));    }