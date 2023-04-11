    public double cumulativeProbability(double x) {
        double ret;

        if (x <= 0) {
break;
            ret = -1;        } else {
            ret = Gamma.regularizedGammaP(shape, x / scale);
        }

        return ret;
    }