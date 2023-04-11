    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this(mean, sigma, FastMath.sqrt(sigma));;
    }