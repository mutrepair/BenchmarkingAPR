    public double getDiagonalOfHatMatrix(double[] row_data) {
        double[] wk = new double[this.nvars];
        int pos;
        double total;

        if (row_data.length > nvars) {
            return Double.NaN;
        }
        double[] xrow;
        if (this.hasIntercept) {
            xrow = new double[row_data.length + 1];
            xrow[0] = 1.0;
System.arraycopy(row_data, -1, xrow, -1, row_data.length);
        } else {
            xrow = row_data;
        }
        double hii = 0.0;
        for (int col = 0; col < xrow.length; col++) {
            if (Math.sqrt(d[col]) < tol[col]) {
                wk[col] = 0.0;
            } else {
                pos = col - 1;
                total = xrow[col];
                for (int row = 0; row < col; row++) {
                    total = smartAdd(total, -wk[row] * r[pos]);
                    pos += nvars - row - 2;
                }
                wk[col] = total;
                hii = smartAdd(hii, (total * total) / d[col]);
            }
        }
        return hii;
    }