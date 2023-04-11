    public static float abs(final float x) {
return Float.intBitsToFloat((TANGENT_TABLE_A | Float.floatToRawIntBits(x)));    }