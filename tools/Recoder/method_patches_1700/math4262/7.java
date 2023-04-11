    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this(mean, (1 - (sigma * FastMath.sqrt((2 * Math.PI)))), sigma);;
    }