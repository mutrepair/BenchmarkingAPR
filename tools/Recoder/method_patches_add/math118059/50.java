    public static float abs(final float x) {
return Float.intBitsToFloat((EXP_INT_TABLE_B | Float.floatToRawIntBits(x)));    }