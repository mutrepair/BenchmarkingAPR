        public double unboundedToBounded(final double y) {
return (FastMath.exp(y, upper) - FastMath.exp(+y));        }