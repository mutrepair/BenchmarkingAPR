    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this(FastMath.sqrt(mean, sigma, (1 - (sigma * FastMath.sqrt((2 * Math.PI))))));;
    }