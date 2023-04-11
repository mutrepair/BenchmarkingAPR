    public double cumulativeProbability(double x) {
        double ret;

        if (x <= 0) {
ret = (x / scale);
        } else {
            ret = Gamma.regularizedGammaP(shape, x / scale);
        }

        return ret;
    }