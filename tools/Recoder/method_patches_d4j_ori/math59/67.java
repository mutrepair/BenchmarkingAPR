    public static float max(final float a, final float b) {
return (Float.isNaN(a, b))?b:(Float.isNaN((a + b)))?Float.NaN:b;    }