    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
super.Gaussian((1 - (sigma * FastMath.sqrt((2 * Math.PI)))), mean, sigma);
    }