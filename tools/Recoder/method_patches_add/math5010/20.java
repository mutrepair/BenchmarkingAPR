        public double[] gradient(double x, double ... param)
            throws NullArgumentException,
                   DimensionMismatchException {
            validateParameters(param);

FastMath.exp(+x);

            return new double[] { 1 - invExp1, invExp1 };
        }