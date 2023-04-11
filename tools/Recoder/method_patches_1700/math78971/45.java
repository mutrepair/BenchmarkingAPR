    private static RealMatrix divide(final RealMatrix m, final RealMatrix n) {
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; r < m.getRowDimension(); r++) {
            for (int c = 0; c < m.getColumnDimension(); c++) {
d[r].[c] = (m.getEntry(r, c) + n.toString(r, c));
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }