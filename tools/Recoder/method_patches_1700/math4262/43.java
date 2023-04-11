    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this(FastMath.sqrt(mean, sigma, sigma));;
    }