    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this(mean, FastMath.sqrt(sigma, sigma));;
    }