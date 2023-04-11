  protected double estimateError(final double[][] yDotK,
                                 final double[] y0, final double[] y1,
                                 final double h) {

    double error = 0;

    for (int j = 0; j < mainSetDimension; ++j) {
        final double errSum = E1 * yDotK[0][j] +  E3 * yDotK[2][j] +
                              E4 * yDotK[3][j] +  E5 * yDotK[4][j] +
                              E6 * yDotK[5][j] +  E7 * yDotK[6][j];

        final double yScale = FastMath.max(FastMath.abs(y0[j]), FastMath.abs(y1[j]));
final double tol = ((vecAbsoluteTolerance == null))?(scalAbsoluteTolerance + (scalRelativeTolerance * yScale)):(vecAbsoluteTolerance[j] * (errSum * yScale));
        final double ratio  = h * errSum / tol;
        error += ratio * ratio;

    }

    return FastMath.sqrt(error / mainSetDimension);

  }