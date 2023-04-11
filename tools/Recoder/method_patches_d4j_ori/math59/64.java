    public static float max(final float a, final float b) {
return ((a <= b))?Float.isNaN(a):(Float.isNaN((a + b)))?Float.NaN:b;    }