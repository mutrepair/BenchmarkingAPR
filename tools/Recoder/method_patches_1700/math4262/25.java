    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this(mean, sigma, (sigma * FastMath.sqrt((2 * Math.PI))));;
    }