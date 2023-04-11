        public double unboundedToBounded(final double y) {
if((upper == null)){
return 0;}            return upper - FastMath.exp(+y);        }