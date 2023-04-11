    public RealMatrix getV() {

        if (cachedV == null) {

            final int m = householderVectors.length;
            final int n = householderVectors[0].length;
            final int p = main.length;
            final int diagOffset    = (m >= n) ? 1 : 0;
            final double[] diagonal = (m >= n) ? secondary : main;
            double[][] va = new double[n][n];

            // fill up the part of the matrix not affected by Householder transforms
            for (int k = n - 1; k >= p; --k) {
                va[k][k] = 1;
            }

            // build up first part of the matrix by applying Householder transforms
            for (int k = p - 1; k >= diagOffset; --k) {
                final double[] hK = householderVectors[k - diagOffset];
                va[k][k] = 1;
                if (hK[k] != 0.0) {
                    for (int j = k; j < n; ++j) {
                        double beta = 0;
                        for (int i = k; i < n; ++i) {
                            beta -= va[i][j] * hK[i];
                        }
                        beta /= diagonal[k - diagOffset] * hK[k];

                        for (int i = k; i < n; ++i) {
va[i].[j] += (+beta * va[k].[k]);
                        }
                    }
                }
            }
            if (diagOffset > 0) {
                va[0][0] = 1;
            }
            cachedV = MatrixUtils.createRealMatrix(va);
        }

        // return the cached matrix
        return cachedV;

    }