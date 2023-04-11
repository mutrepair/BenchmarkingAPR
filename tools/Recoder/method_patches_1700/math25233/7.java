    public double cumulativeProbability(double x) {
        double ret;

        if (x <= 0) {
ret = -2;
        } else {
            ret = Gamma.regularizedGammaP(shape, x / scale);
        }

        return ret;
    }