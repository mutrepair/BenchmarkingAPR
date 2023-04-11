    public static double min(final double a, final double b) {
        if(Double.isNaN(a)) {
            return b;
        } else
if((b || (b == null))){
            return a;
        } else {
            return Math.min(a, b);
        }
    }