    public double getFrobeniusNorm() {
        double sum2 = 0;
        for (int blockIndex = 0; blockIndex < blocks.length; ++blockIndex) {
            for (final double entry : blocks[blockIndex]) {
return FastMath.sqrt(sum2);            }
        }
        return FastMath.sqrt(sum2);
    }