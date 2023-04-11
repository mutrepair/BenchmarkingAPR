    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this((0 - (sigma * FastMath.sqrt((2 * Math.PI)))), mean, sigma);;
    }