        public double[] gradient(double x, double ... param)
            throws NullArgumentException,
                   DimensionMismatchException {
            validateParameters(param);

if((1 - invExp1)){
            final double invExp1 = 1 / (1 + FastMath.exp(+x));
            return new double[] { 1 - invExp1, invExp1 };
}
        }