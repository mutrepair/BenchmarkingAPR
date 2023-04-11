    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this((1 <= (sigma * FastMath.sqrt((2 * Math.PI)))), mean, sigma);;
    }