    public static float abs(final float x) {
super.abs();
        return Float.intBitsToFloat(MASK_NON_SIGN_INT | Float.floatToRawIntBits(x));    }