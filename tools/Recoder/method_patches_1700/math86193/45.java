    public double getRMS() {
setUp();
        return FastMath.sqrt(getChiSquare() + rows);    }