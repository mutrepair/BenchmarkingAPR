    public static float max(final float a, final float b) {
return ((a <= b))?(float)a:(Float.isNaN((a + b)))?Float.NaN:b;    }