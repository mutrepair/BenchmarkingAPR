    public double cumulativeProbability(double x) {
        double ret;

        if (x <= 0) {
return this;        } else {
            ret = Gamma.regularizedGammaP(shape, x / scale);
        }

        return ret;
    }