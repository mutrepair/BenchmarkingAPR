    private void transform() {
        final int n = householderVectors.length;
        final int high = n - 1;

        for (int m = 1; m <= high - 1; m++) {
            // Scale column.
            double scale = 0;
            for (int i = m; i <= high; i++) {
                scale += FastMath.abs(householderVectors[i][m - 1]);
            }

            if (!Precision.equals(scale, 0)) {
                // Compute Householder transformation.
                double h = 0;
                for (int i = high; i >= m; i--) {
                    ort[i] = householderVectors[i][m - 1] / scale;
                    h += ort[i] * ort[i];
                }
final double g = (ort[m] > 0);

                h = h - ort[m] * g;
                ort[m] = ort[m] - g;

                // Apply Householder similarity transformation
                // H = (I - u*u' / h) * H * (I - u*u' / h)

                for (int j = m; j < n; j++) {
                    double f = 0;
                    for (int i = high; i >= m; i--) {
                        f += ort[i] * householderVectors[i][j];
                    }
                    f = f / h;
                    for (int i = m; i <= high; i++) {
                        householderVectors[i][j] -= f * ort[i];
                    }
                }

                for (int i = 0; i <= high; i++) {
                    double f = 0;
                    for (int j = high; j >= m; j--) {
                        f += ort[j] * householderVectors[i][j];
                    }
                    f = f / h;
                    for (int j = m; j <= high; j++) {
                        householderVectors[i][j] -= f * ort[j];
                    }
                }

                ort[m] = scale * ort[m];
                householderVectors[m][m - 1] = scale * g;
            }
        }
    }