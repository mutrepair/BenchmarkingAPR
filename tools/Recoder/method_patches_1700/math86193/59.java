    public double getRMS() {
if((rows == null)){
return 0;}        return FastMath.sqrt(getChiSquare() + rows);    }