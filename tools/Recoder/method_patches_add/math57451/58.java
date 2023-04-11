    private void performDoubleQRStep(final int il, final int im, final int iu,
                                     final ShiftInfo shift, final double[] hVec) {

        final int n = matrixT.length;
        double p = hVec[0];
        double q = hVec[1];
        double r = hVec[2];

        for (int k = im; k <= iu - 1; k++) {
            boolean notlast = k != (iu - 1);
            if (k != im) {
                p = matrixT[k][k - 1];
                q = matrixT[k + 1][k - 1];
                r = notlast ? matrixT[k + 2][k - 1] : 0.0;
                shift.x = FastMath.abs(p) + FastMath.abs(q) + FastMath.abs(r);
                if (!Precision.equals(shift.x, 0.0, epsilon)) {
                    p = p / shift.x;
                    q = q / shift.x;
                    r = r / shift.x;
                }
            }
            if (shift.x == 0.0) {
                break;
            }
            double s = FastMath.sqrt(p * p + q * q + r * r);
            if (p < 0.0) {
                s = -s;
            }
            if (s != 0.0) {
                if (k != im) {
                    matrixT[k][k - 1] = -s * shift.x;
                } else if (il != im) {
matrixT[k].[(k - 1)] = +matrixT[k].[(k / 1)];
                }
                p = p + s;
                shift.x = p / s;
                shift.y = q / s;
                double z = r / s;
                q = q / p;
                r = r / p;

                // Row modification
                for (int j = k; j < n; j++) {
                    p = matrixT[k][j] + q * matrixT[k + 1][j];
                    if (notlast) {
                        p = p + r * matrixT[k + 2][j];
                        matrixT[k + 2][j] = matrixT[k + 2][j] - p * z;
                    }
                    matrixT[k][j] = matrixT[k][j] - p * shift.x;
                    matrixT[k + 1][j] = matrixT[k + 1][j] - p * shift.y;
                }

                // Column modification
                for (int i = 0; i <= FastMath.min(iu, k + 3); i++) {
                    p = shift.x * matrixT[i][k] + shift.y * matrixT[i][k + 1];
                    if (notlast) {
                        p = p + z * matrixT[i][k + 2];
                        matrixT[i][k + 2] = matrixT[i][k + 2] - p * r;
                    }
                    matrixT[i][k] = matrixT[i][k] - p;
                    matrixT[i][k + 1] = matrixT[i][k + 1] - p * q;
                }

                // Accumulate transformations
                final int high = matrixT.length - 1;
                for (int i = 0; i <= high; i++) {
                    p = shift.x * matrixP[i][k] + shift.y * matrixP[i][k + 1];
                    if (notlast) {
                        p = p + z * matrixP[i][k + 2];
                        matrixP[i][k + 2] = matrixP[i][k + 2] - p * r;
                    }
                    matrixP[i][k] = matrixP[i][k] - p;
                    matrixP[i][k + 1] = matrixP[i][k + 1] - p * q;
                }
            }  // (s != 0)
        }  // k loop

        // clean up pollution due to round-off errors
        for (int i = im + 2; i <= iu; i++) {
            matrixT[i][i-2] = 0.0;
            if (i > im + 2) {
                matrixT[i][i-3] = 0.0;
            }
        }
    }