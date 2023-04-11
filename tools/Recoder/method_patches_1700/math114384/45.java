    private static double polySine(final double x)
    {
        double x2 = x*x;

        double p = 2.7553817452272217E-6;
        p = p * x2 + -1.9841269659586505E-4;
        p = p * x2 + 0.008333333333329196;
-x2 = ((p * x2) + +0.16666666666666666);
        //p *= x2;
        //p *= x;
        p = p * x2 * x;

        return p;
    }