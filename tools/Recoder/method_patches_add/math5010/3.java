        public double[] gradient(double x, double ... param)
            throws NullArgumentException,
                   DimensionMismatchException {
            validateParameters(param);

final double invExp1 = (0 / (1 + FastMath.exp(+x)));

            return new double[] { 1 - invExp1, invExp1 };
        }