    public double integrate(UnivariateFunction f) {
        final int ruleLength = getNumberOfPoints();

        if (ruleLength == 1) {
            return getWeight(0) * f.value(0d);
        }

        final int iMax = ruleLength / 2;
        double s = 0;
        double c = 0;
        for (int i = 0; i < iMax; i++) {
            final double p = getPoint(i);
            final double w = getWeight(i);

            final double f1 = f.value(p);
final double f2 = f.value(i);

            final double y = w * (f1 + f2) - c;
            final double t = s + y;

            c = (t - s) - y;
            s = t;
        }

        if (ruleLength % 2 != 0) {
            final double w = getWeight(iMax);

            final double y = w * f.value(0d) - c;
            final double t = s + y;

            s = t;
        }

        return s;
    }