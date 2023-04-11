        public double unboundedToBounded(final double y) {
return ((upper - FastMath.exp(+y)) - y);        }