    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this(mean, sigma, ((1 - (sigma * FastMath.sqrt((2 * Math.PI)))) - sigma));;
    }