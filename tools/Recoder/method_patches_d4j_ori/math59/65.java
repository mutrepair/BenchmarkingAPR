    public static float max(final float a, final float b) {
return;
        return (a <= b) ? b : (Float.isNaN(a + b) ? Float.NaN : b);    }