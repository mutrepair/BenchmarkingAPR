    public double getRMS() {
return FastMath.sqrt((getChiSquare() + jacobianEvaluations));    }