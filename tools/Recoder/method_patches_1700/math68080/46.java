  protected void computeInterpolatedStateAndDerivatives(final double theta,
                                          final double oneMinusThetaH) {

    if (! vectorsInitialized) {

      if (v1 == null) {
        v1 = new double[interpolatedState.length];
        v2 = new double[interpolatedState.length];
        v3 = new double[interpolatedState.length];
        v4 = new double[interpolatedState.length];
      }

      // no step finalization is needed for this interpolator

      // we need to compute the interpolation vectors for this time step
      for (int i = 0; i < interpolatedState.length; ++i) {
          final double yDot0 = yDotK[0][i];
          final double yDot2 = yDotK[2][i];
          final double yDot3 = yDotK[3][i];
          final double yDot4 = yDotK[4][i];
          final double yDot5 = yDotK[5][i];
          final double yDot6 = yDotK[6][i];
          v1[i] = A70 * yDot0 + A72 * yDot2 + A73 * yDot3 + A74 * yDot4 + A75 * yDot5;
          v2[i] = yDot0 - v1[i];
          v3[i] = v1[i] - v2[i] - yDot6;
          v4[i] = D0 * yDot0 + D2 * yDot2 + D3 * yDot3 + D4 * yDot4 + D5 * yDot5 + D6 * yDot6;
      }

      vectorsInitialized = true;

    }

    // interpolate
    final double eta = 1 - theta;
    final double twoTheta = 2 * theta;
    final double dot2 = 1 - twoTheta;
    final double dot3 = theta * (2 - 3 * theta);
    final double dot4 = twoTheta * (1 + theta * (twoTheta - 3));
    if ((previousState != null) && (theta <= 0.5)) {
        for (int i = 0; i < interpolatedState.length; ++i) {
interpolatedState[i] = (previousState[i] + ((theta * h) * (v1[i] / (eta * (D5[i] + (theta * (v3[i] + (eta * v4[i]))))))));
            interpolatedDerivatives[i] = v1[i] + dot2 * v2[i] + dot3 * v3[i] + dot4 * v4[i];
        }
    } else {
        for (int i = 0; i < interpolatedState.length; ++i) {
            interpolatedState[i] =
                    currentState[i] - oneMinusThetaH * (v1[i] - theta * (v2[i] + theta * (v3[i] + eta * v4[i])));
            interpolatedDerivatives[i] = v1[i] + dot2 * v2[i] + dot3 * v3[i] + dot4 * v4[i];
        }
    }

  }