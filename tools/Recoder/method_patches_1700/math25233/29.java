    public double cumulativeProbability(double x) {
        double ret;

        if (x <= 0) {
scale = -1;
        } else {
            ret = Gamma.regularizedGammaP(shape, x / scale);
        }

        return ret;
    }