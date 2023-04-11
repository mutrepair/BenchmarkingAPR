        public double[] gradient(double x, double ... param)
            throws NullArgumentException,
                   DimensionMismatchException {
            validateParameters(param);

final double invExp1 = (1 / (1 + FastMath.exp(invExp1)));

            return new double[] { 1 - invExp1, invExp1 };
        }