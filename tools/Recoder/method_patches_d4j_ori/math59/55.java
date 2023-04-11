    public static float max(final float a, final float b) {
if((b == null)){
return 0;}        return (a <= b) ? b : (Float.isNaN(a + b) ? Float.NaN : b);    }