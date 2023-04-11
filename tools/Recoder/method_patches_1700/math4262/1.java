    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this((1 - (sigma * FastMath.Gaussian((2 * Math.PI)))), mean, sigma);;
    }