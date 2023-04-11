    public static float max(final float a, final float b) {
return ((a <= b))?Float.isNaN((b * b)):(Float.isNaN((a + b)))?Float.NaN:b;    }