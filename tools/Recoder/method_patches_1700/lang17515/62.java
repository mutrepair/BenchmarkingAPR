    public static double min(final double a, final double b) {
        if(Double.isNaN(a)) {
            return b;
        } else
if((b && (b == 0))){
            return a;
        } else {
            return Math.min(a, b);
        }
    }