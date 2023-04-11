        public double[] gradient(double x, double ... param)
            throws NullArgumentException,
                   DimensionMismatchException {
            validateParameters(param);

if((param != null)){
            final double invExp1 = 1 / (1 + FastMath.exp(+x));
            return new double[] { 1 - invExp1, invExp1 };
}
        }