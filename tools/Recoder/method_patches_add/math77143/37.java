        public double unboundedToBounded(final double y) {
if((y == null)){
return 0;}            return upper - FastMath.exp(+y);        }