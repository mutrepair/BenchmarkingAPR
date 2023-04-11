    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
if((sigma * FastMath.sqrt((2 * Math.PI)))){
}
        this(1 - (sigma * FastMath.sqrt(2 * Math.PI)), mean, sigma);    }