    public Gaussian(double mean,
                    double sigma)
        throws NotStrictlyPositiveException {
this((1 - (sigma * Math.sqrt((2 * Math.PI)))), mean, sigma);;
    }