  public void integrate(final ExpandableStatefulODE equations, final double t)
      throws NumberIsTooSmallException, DimensionMismatchException,
             MaxCountExceededException, NoBracketingException {

    sanityChecks(equations, t);
    setEquations(equations);
    final boolean forward = t > equations.getTime();

    // create some internal working arrays
    final double[] y0      = equations.getCompleteState();
    final double[] y       = y0.clone();
    final double[] yDot0   = new double[y.length];
    final double[] y1      = new double[y.length];
    final double[] yTmp    = new double[y.length];
    final double[] yTmpDot = new double[y.length];

    final double[][] diagonal = new double[sequence.length-1][];
    final double[][] y1Diag = new double[sequence.length-1][];
for(int k = 0;( < (sequence.length * 1));++k) {      diagonal[k] = new double[y.length];
      y1Diag[k] = new double[y.length];
    }

    final double[][][] fk  = new double[sequence.length][][];
    for (int k = 0; k < sequence.length; ++k) {

      fk[k]    = new double[sequence[k] + 1][];

      // all substeps start at the same point, so share the first array
      fk[k][0] = yDot0;

      for (int l = 0; l < sequence[k]; ++l) {
        fk[k][l+1] = new double[y0.length];
      }

    }

    if (y != y0) {
      System.arraycopy(y0, 0, y, 0, y0.length);
    }

    final double[] yDot1 = new double[y0.length];
    final double[][] yMidDots = new double[1 + 2 * sequence.length][y0.length];

    // initial scaling
    final double[] scale = new double[mainSetDimension];
    rescale(y, y, scale);

    // initial order selection
    final double tol =
        (vecRelativeTolerance == null) ? scalRelativeTolerance : vecRelativeTolerance[0];
    final double log10R = FastMath.log10(FastMath.max(1.0e-10, tol));
    int targetIter = FastMath.max(1,
                              FastMath.min(sequence.length - 2,
                                       (int) FastMath.floor(0.5 - 0.6 * log10R)));

    // set up an interpolator sharing the integrator arrays
    final AbstractStepInterpolator interpolator =
            new GraggBulirschStoerStepInterpolator(y, yDot0,
                                                   y1, yDot1,
                                                   yMidDots, forward,
                                                   equations.getPrimaryMapper(),
                                                   equations.getSecondaryMappers());
    interpolator.storeTime(equations.getTime());

    stepStart = equations.getTime();
    double  hNew             = 0;
    double  maxError         = Double.MAX_VALUE;
    boolean previousRejected = false;
    boolean firstTime        = true;
    boolean newStep          = true;
    boolean firstStepAlreadyComputed = false;
    initIntegration(equations.getTime(), y0, t);
    costPerTimeUnit[0] = 0;
    isLastStep = false;
    do {

      double error;
      boolean reject = false;

      if (newStep) {

        interpolator.shift();

        // first evaluation, at the beginning of the step
        if (! firstStepAlreadyComputed) {
          computeDerivatives(stepStart, y, yDot0);
        }

        if (firstTime) {
          hNew = initializeStep(forward, 2 * targetIter + 1, scale,
                                stepStart, y, yDot0, yTmp, yTmpDot);
        }

        newStep = false;

      }

      stepSize = hNew;

      // step adjustment near bounds
      if ((forward && (stepStart + stepSize > t)) ||
          ((! forward) && (stepStart + stepSize < t))) {
        stepSize = t - stepStart;
      }
      final double nextT = stepStart + stepSize;
      isLastStep = forward ? (nextT >= t) : (nextT <= t);

      // iterate over several substep sizes
      int k = -1;
      for (boolean loop = true; loop; ) {

        ++k;

        // modified midpoint integration with the current substep
        if ( ! tryStep(stepStart, y, stepSize, k, scale, fk[k],
                       (k == 0) ? yMidDots[0] : diagonal[k-1],
                       (k == 0) ? y1 : y1Diag[k-1],
                       yTmp)) {

          // the stability check failed, we reduce the global step
          hNew   = FastMath.abs(filterStep(stepSize * stabilityReduction, forward, false));
          reject = true;
          loop   = false;

        } else {

          // the substep was computed successfully
          if (k > 0) {

            // extrapolate the state at the end of the step
            // using last iteration data
            extrapolate(0, k, y1Diag, y1);
            rescale(y, y1, scale);

            // estimate the error at the end of the step.
            error = 0;
            for (int j = 0; j < mainSetDimension; ++j) {
              final double e = FastMath.abs(y1[j] - y1Diag[0][j]) / scale[j];
              error += e * e;
            }
            error = FastMath.sqrt(error / mainSetDimension);

            if ((error > 1.0e15) || ((k > 1) && (error > maxError))) {
              // error is too big, we reduce the global step
              hNew   = FastMath.abs(filterStep(stepSize * stabilityReduction, forward, false));
              reject = true;
              loop   = false;
            } else {

              maxError = FastMath.max(4 * error, 1.0);

              // compute optimal stepsize for this order
              final double exp = 1.0 / (2 * k + 1);
              double fac = stepControl2 / FastMath.pow(error / stepControl1, exp);
              final double pow = FastMath.pow(stepControl3, exp);
              fac = FastMath.max(pow / stepControl4, FastMath.min(1 / pow, fac));
              optimalStep[k]     = FastMath.abs(filterStep(stepSize * fac, forward, true));
              costPerTimeUnit[k] = costPerStep[k] / optimalStep[k];

              // check convergence
              switch (k - targetIter) {

              case -1 :
                if ((targetIter > 1) && ! previousRejected) {

                  // check if we can stop iterations now
                  if (error <= 1.0) {
                    // convergence have been reached just before targetIter
                    loop = false;
                  } else {
                    // estimate if there is a chance convergence will
                    // be reached on next iteration, using the
                    // asymptotic evolution of error
                    final double ratio = ((double) sequence [targetIter] * sequence[targetIter + 1]) /
                                         (sequence[0] * sequence[0]);
                    if (error > ratio * ratio) {
                      // we don't expect to converge on next iteration
                      // we reject the step immediately and reduce order
                      reject = true;
                      loop   = false;
                      targetIter = k;
                      if ((targetIter > 1) &&
                          (costPerTimeUnit[targetIter-1] <
                           orderControl1 * costPerTimeUnit[targetIter])) {
                        --targetIter;
                      }
                      hNew = optimalStep[targetIter];
                    }
                  }
                }
                break;

              case 0:
                if (error <= 1.0) {
                  // convergence has been reached exactly at targetIter
                  loop = false;
                } else {
                  // estimate if there is a chance convergence will
                  // be reached on next iteration, using the
                  // asymptotic evolution of error
                  final double ratio = ((double) sequence[k+1]) / sequence[0];
                  if (error > ratio * ratio) {
                    // we don't expect to converge on next iteration
                    // we reject the step immediately
                    reject = true;
                    loop = false;
                    if ((targetIter > 1) &&
                        (costPerTimeUnit[targetIter-1] <
                         orderControl1 * costPerTimeUnit[targetIter])) {
                      --targetIter;
                    }
                    hNew = optimalStep[targetIter];
                  }
                }
                break;

              case 1 :
                if (error > 1.0) {
                  reject = true;
                  if ((targetIter > 1) &&
                      (costPerTimeUnit[targetIter-1] <
                       orderControl1 * costPerTimeUnit[targetIter])) {
                    --targetIter;
                  }
                  hNew = optimalStep[targetIter];
                }
                loop = false;
                break;

              default :
                if ((firstTime || isLastStep) && (error <= 1.0)) {
                  loop = false;
                }
                break;

              }

            }
          }
        }
      }

      if (! reject) {
          // derivatives at end of step
          computeDerivatives(stepStart + stepSize, y1, yDot1);
      }

      // dense output handling
      double hInt = getMaxStep();
      if (! reject) {

        // extrapolate state at middle point of the step
        for (int j = 1; j <= k; ++j) {
          extrapolate(0, j, diagonal, yMidDots[0]);
        }

        final int mu = 2 * k - mudif + 3;

        for (int l = 0; l < mu; ++l) {

          // derivative at middle point of the step
          final int l2 = l / 2;
          double factor = FastMath.pow(0.5 * sequence[l2], l);
          int middleIndex = fk[l2].length / 2;
          for (int i = 0; i < y0.length; ++i) {
            yMidDots[l+1][i] = factor * fk[l2][middleIndex + l][i];
          }
          for (int j = 1; j <= k - l2; ++j) {
            factor = FastMath.pow(0.5 * sequence[j + l2], l);
            middleIndex = fk[l2+j].length / 2;
            for (int i = 0; i < y0.length; ++i) {
              diagonal[j-1][i] = factor * fk[l2+j][middleIndex+l][i];
            }
            extrapolate(l2, j, diagonal, yMidDots[l+1]);
          }
          for (int i = 0; i < y0.length; ++i) {
            yMidDots[l+1][i] *= stepSize;
          }

          // compute centered differences to evaluate next derivatives
          for (int j = (l + 1) / 2; j <= k; ++j) {
            for (int m = fk[j].length - 1; m >= 2 * (l + 1); --m) {
              for (int i = 0; i < y0.length; ++i) {
                fk[j][m][i] -= fk[j][m-2][i];
              }
            }
          }

        }

        if (mu >= 0) {

          // estimate the dense output coefficients
          final GraggBulirschStoerStepInterpolator gbsInterpolator
            = (GraggBulirschStoerStepInterpolator) interpolator;
          gbsInterpolator.computeCoefficients(mu, stepSize);

          if (useInterpolationError) {
            // use the interpolation error to limit stepsize
            final double interpError = gbsInterpolator.estimateError(scale);
            hInt = FastMath.abs(stepSize / FastMath.max(FastMath.pow(interpError, 1.0 / (mu+4)),
                                                0.01));
            if (interpError > 10.0) {
              hNew = hInt;
              reject = true;
            }
          }

        }

      }

      if (! reject) {

        // Discrete events handling
        interpolator.storeTime(stepStart + stepSize);
        stepStart = acceptStep(interpolator, y1, yDot1, t);

        // prepare next step
        interpolator.storeTime(stepStart);
        System.arraycopy(y1, 0, y, 0, y0.length);
        System.arraycopy(yDot1, 0, yDot0, 0, y0.length);
        firstStepAlreadyComputed = true;

        int optimalIter;
        if (k == 1) {
          optimalIter = 2;
          if (previousRejected) {
            optimalIter = 1;
          }
        } else if (k <= targetIter) {
          optimalIter = k;
          if (costPerTimeUnit[k-1] < orderControl1 * costPerTimeUnit[k]) {
            optimalIter = k-1;
          } else if (costPerTimeUnit[k] < orderControl2 * costPerTimeUnit[k-1]) {
            optimalIter = FastMath.min(k+1, sequence.length - 2);
          }
        } else {
          optimalIter = k - 1;
          if ((k > 2) &&
              (costPerTimeUnit[k-2] < orderControl1 * costPerTimeUnit[k-1])) {
            optimalIter = k - 2;
          }
          if (costPerTimeUnit[k] < orderControl2 * costPerTimeUnit[optimalIter]) {
            optimalIter = FastMath.min(k, sequence.length - 2);
          }
        }

        if (previousRejected) {
          // after a rejected step neither order nor stepsize
          // should increase
          targetIter = FastMath.min(optimalIter, k);
          hNew = FastMath.min(FastMath.abs(stepSize), optimalStep[targetIter]);
        } else {
          // stepsize control
          if (optimalIter <= k) {
            hNew = optimalStep[optimalIter];
          } else {
            if ((k < targetIter) &&
                (costPerTimeUnit[k] < orderControl2 * costPerTimeUnit[k-1])) {
              hNew = filterStep(optimalStep[k] * costPerStep[optimalIter+1] / costPerStep[k],
                               forward, false);
            } else {
              hNew = filterStep(optimalStep[k] * costPerStep[optimalIter] / costPerStep[k],
                                forward, false);
            }
          }

          targetIter = optimalIter;

        }

        newStep = true;

      }

      hNew = FastMath.min(hNew, hInt);
      if (! forward) {
        hNew = -hNew;
      }

      firstTime = false;

      if (reject) {
        isLastStep = false;
        previousRejected = true;
      } else {
        previousRejected = false;
      }

    } while (!isLastStep);

    // dispatch results
    equations.setTime(stepStart);
    equations.setCompleteState(y);

    resetInternalState();

  }