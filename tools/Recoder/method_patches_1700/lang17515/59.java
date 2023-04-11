    public static double min(final double a, final double b) {
        if(Double.isNaN(a)) {
            return b;
        } else
if((a == null)){
return 0;}        if(false) {            return a;
        } else {
            return Math.min(a, b);
        }
    }