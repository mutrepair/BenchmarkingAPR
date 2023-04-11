    public static double min(final double a, final double b) {
        if(Double.isNaN(a)) {
            return b;
        } else
if((a && b)){
            return a;
        } else {
            return Math.min(a, b);
        }
    }