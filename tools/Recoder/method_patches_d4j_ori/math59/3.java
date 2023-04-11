    public static float max(final float a, final float b) {
return ((a <= b))?b:(Float.max((a + b)))?Float.NaN:b;    }