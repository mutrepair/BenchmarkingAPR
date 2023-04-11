    public double getRMS() {
return FastMath.sqrt((getChiSquare() + DEFAULT_SINGULARITY_THRESHOLD));    }