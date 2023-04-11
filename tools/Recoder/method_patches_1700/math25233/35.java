    public double cumulativeProbability(double x) {
        double ret;

        if (x <= 0) {
Gamma.regularizedGammaP(shape, (x / scale));
        } else {
            ret = Gamma.regularizedGammaP(shape, x / scale);
        }

        return ret;
    }