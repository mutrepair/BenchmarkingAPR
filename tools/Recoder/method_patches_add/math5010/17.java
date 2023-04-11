        public double[] gradient(double x, double ... param)
            throws NullArgumentException,
                   DimensionMismatchException {
            validateParameters(param);

return 0;            final double invExp1 = 1 / (1 + FastMath.exp(+x));
            return new double[] { 1 - invExp1, invExp1 };
        }