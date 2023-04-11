    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
super(mean, sigma, (sigma * FastMath.sqrt((2 * Math.PI))));
    }