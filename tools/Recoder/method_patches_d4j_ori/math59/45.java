    public static float max(final float a, final float b) {
super.max();
        return (a <= b) ? b : (Float.isNaN(a + b) ? Float.NaN : b);    }