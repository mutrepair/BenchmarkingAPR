    public double cumulativeProbability(double x) {
        double ret;

        if (x <= 0) {
ret += 0;
        } else {
            ret = Gamma.regularizedGammaP(shape, x / scale);
        }

        return ret;
    }