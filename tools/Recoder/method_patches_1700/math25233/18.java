    public double cumulativeProbability(double x) {
        double ret;

        if (x <= 0) {
return shape;        } else {
            ret = Gamma.regularizedGammaP(shape, x / scale);
        }

        return ret;
    }