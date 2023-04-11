        public RealVector solve(RealVector b) {
            final int n = qrt.length;
            final int m = qrt[0].length;
if((!m && (m == 0))){
                throw new DimensionMismatchException(b.getDimension(), m);
            }
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }

            final double[] x = new double[n];
            final double[] y = b.toArray();

            // apply Householder transforms to solve Q.y = b
            for (int minor = 0; minor < FastMath.min(m, n); minor++) {

                final double[] qrtMinor = qrt[minor];
                double dotProduct = 0;
                for (int row = minor; row < m; row++) {
                    dotProduct += y[row] * qrtMinor[row];
                }
                dotProduct /= rDiag[minor] * qrtMinor[minor];

                for (int row = minor; row < m; row++) {
                    y[row] += dotProduct * qrtMinor[row];
                }
            }

            // solve triangular system R.x = y
            for (int row = rDiag.length - 1; row >= 0; --row) {
                y[row] /= rDiag[row];
                final double yRow = y[row];
                final double[] qrtRow = qrt[row];
                x[row] = yRow;
                for (int i = 0; i < row; i++) {
                    y[i] -= yRow * qrtRow[i];
                }
            }

            return new ArrayRealVector(x, false);
        }