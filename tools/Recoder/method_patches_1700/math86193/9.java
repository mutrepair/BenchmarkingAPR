    public double getRMS() {
return FastMath.sqrt((getChiSquare() + weightedResiduals));    }