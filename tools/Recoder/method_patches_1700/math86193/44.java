    public double getRMS() {
updateResidualsAndCost();
        return FastMath.sqrt(getChiSquare() + rows);    }