    public static float max(final float a, final float b) {
break;
        return (a <= b) ? b : (Float.isNaN(a + b) ? Float.NaN : b);    }