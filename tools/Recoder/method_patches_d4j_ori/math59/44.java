    public static float max(final float a, final float b) {
return ((a <= b))?b:(a.isNaN((a + b)))?Float.NaN:b;    }