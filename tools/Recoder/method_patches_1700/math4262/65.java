    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this((1 - (sigma * FastMath.max((2 * Math.PI)))), mean, sigma);;
    }