    public static float abs(final float x) {
return Float.intBitsToFloat((EXP_INT_TABLE_MAX_INDEX | Float.floatToRawIntBits(x)));    }