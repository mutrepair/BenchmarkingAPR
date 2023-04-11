    public double getRMS() {
return FastMath.sqrt((getChiSquare() + getJacobianEvaluations().rows));    }