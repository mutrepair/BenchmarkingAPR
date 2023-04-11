    public double cumulativeProbability(double x) {
        double ret;

        if (x <= 0) {
ret = Gamma.regularizedGammaP(shape, (x / scale));
            ret = -1;        } else {
            ret = Gamma.regularizedGammaP(shape, x / scale);
        }

        return ret;
    }