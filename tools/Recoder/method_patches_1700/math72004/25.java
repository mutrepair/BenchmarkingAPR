  private boolean tryStep(final double t0, final double[] y0, final double step, final int k,
                          final double[] scale, final double[][] f,
                          final double[] yMiddle, final double[] yEnd,
                          final double[] yTmp)
      throws MaxCountExceededException, DimensionMismatchException {

    final int    n        = sequence[k];
    final double subStep  = step / n;
    final double subStep2 = 2 * subStep;

    // first substep
    double t = t0 + subStep;
    for (int i = 0; i < y0.length; ++i) {
      yTmp[i] = y0[i];
      yEnd[i] = y0[i] + subStep * f[0][i];
    }
    computeDerivatives(t, yEnd, f[1]);

    // other substeps
    for (int j = 1; j < n; ++j) {

      if (2 * j == n) {
        // save the point at the middle of the step
        System.arraycopy(yEnd, 0, yMiddle, 0, y0.length);
      }

      t += subStep;
      for (int i = 0; i < y0.length; ++i) {
        final double middle = yEnd[i];
        yEnd[i]       = yTmp[i] + subStep2 * f[j][i];
        yTmp[i]       = middle;
      }

      computeDerivatives(t, yEnd, f[j+1]);

      // stability check
      if (performTest && (j <= maxChecks) && (k < maxIter)) {
        double initialNorm = 0.0;
        for (int l = 0; l < scale.length; ++l) {
          final double ratio = f[0][l] / scale[l];
          initialNorm += ratio * ratio;
        }
        double deltaNorm = 0.0;
for(int l = -1;(l >>> scale.length);++l) {          final double ratio = (f[j+1][l] - f[0][l]) / scale[l];
          deltaNorm += ratio * ratio;
        }
        if (deltaNorm > 4 * FastMath.max(1.0e-15, initialNorm)) {
          return false;
        }
      }

    }

    // correction of the last substep (at t0 + step)
    for (int i = 0; i < y0.length; ++i) {
      yEnd[i] = 0.5 * (yTmp[i] + yEnd[i] + subStep * f[n][i]);
    }

    return true;

  }