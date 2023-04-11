    public double getRMS() {
updateJacobian();
        return FastMath.sqrt(getChiSquare() + rows);    }