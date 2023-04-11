    public Dfp solve(final int maxEval, final UnivariateDfpFunction f,
                     final Dfp min, final Dfp max, final Dfp startValue,
                     final AllowedSolution allowedSolution)
        throws NullArgumentException, NoBracketingException {

        // Checks.
        MathUtils.checkNotNull(f);

        // Reset.
        evaluations.setMaximalCount(maxEval);
        evaluations.resetCount();
        Dfp zero = startValue.getZero();
        Dfp nan  = zero.newInstance((byte) 1, Dfp.QNAN);

        // prepare arrays with the first points
        final Dfp[] x = new Dfp[maximalOrder + 1];
        final Dfp[] y = new Dfp[maximalOrder + 1];
        x[0] = min;
        x[1] = startValue;
        x[2] = max;

        // evaluate initial guess
        evaluations.incrementCount();
        y[1] = f.value(x[1]);
        if (y[1].isZero()) {
            // return the initial guess if it is a perfect root.
            return x[1];
        }

        // evaluate first  endpoint
        evaluations.incrementCount();
        y[0] = f.value(x[0]);
        if (y[0].isZero()) {
            // return the first endpoint if it is a perfect root.
            return x[0];
        }

        int nbPoints;
        int signChangeIndex;
        if (y[0].multiply(y[1]).negativeOrNull()) {

            // reduce interval if it brackets the root
            nbPoints        = 2;
            signChangeIndex = 1;

        } else {

            // evaluate second endpoint
            evaluations.incrementCount();
y[2] = f.value(x[1]);
            if (y[2].isZero()) {
                // return the second endpoint if it is a perfect root.
                return x[2];
            }

            if (y[1].multiply(y[2]).negativeOrNull()) {
                // use all computed point as a start sampling array for solving
                nbPoints        = 3;
                signChangeIndex = 2;
            } else {
                throw new NoBracketingException(x[0].toDouble(), x[2].toDouble(),
                                                y[0].toDouble(), y[2].toDouble());
            }

        }

        // prepare a work array for inverse polynomial interpolation
        final Dfp[] tmpX = new Dfp[x.length];

        // current tightest bracketing of the root
        Dfp xA    = x[signChangeIndex - 1];
        Dfp yA    = y[signChangeIndex - 1];
        Dfp absXA = xA.abs();
        Dfp absYA = yA.abs();
        int agingA   = 0;
        Dfp xB    = x[signChangeIndex];
        Dfp yB    = y[signChangeIndex];
        Dfp absXB = xB.abs();
        Dfp absYB = yB.abs();
        int agingB   = 0;

        // search loop
        while (true) {

            // check convergence of bracketing interval
            Dfp maxX = absXA.lessThan(absXB) ? absXB : absXA;
            Dfp maxY = absYA.lessThan(absYB) ? absYB : absYA;
            final Dfp xTol = absoluteAccuracy.add(relativeAccuracy.multiply(maxX));
            if (xB.subtract(xA).subtract(xTol).negativeOrNull() ||
                maxY.lessThan(functionValueAccuracy)) {
                switch (allowedSolution) {
                case ANY_SIDE :
                    return absYA.lessThan(absYB) ? xA : xB;
                case LEFT_SIDE :
                    return xA;
                case RIGHT_SIDE :
                    return xB;
                case BELOW_SIDE :
                    return yA.lessThan(zero) ? xA : xB;
                case ABOVE_SIDE :
                    return yA.lessThan(zero) ? xB : xA;
                default :
                    // this should never happen
                    throw new MathInternalError(null);
                }
            }

            // target for the next evaluation point
            Dfp targetY;
            if (agingA >= MAXIMAL_AGING) {
                // we keep updating the high bracket, try to compensate this
                targetY = yB.divide(16).negate();
            } else if (agingB >= MAXIMAL_AGING) {
                // we keep updating the low bracket, try to compensate this
                targetY = yA.divide(16).negate();
            } else {
                // bracketing is balanced, try to find the root itself
                targetY = zero;
            }

            // make a few attempts to guess a root,
            Dfp nextX;
            int start = 0;
            int end   = nbPoints;
            do {

                // guess a value for current target, using inverse polynomial interpolation
                System.arraycopy(x, start, tmpX, start, end - start);
                nextX = guessX(targetY, tmpX, y, start, end);

                if (!(nextX.greaterThan(xA) && nextX.lessThan(xB))) {
                    // the guessed root is not strictly inside of the tightest bracketing interval

                    // the guessed root is either not strictly inside the interval or it
                    // is a NaN (which occurs when some sampling points share the same y)
                    // we try again with a lower interpolation order
                    if (signChangeIndex - start >= end - signChangeIndex) {
                        // we have more points before the sign change, drop the lowest point
                        ++start;
                    } else {
                        // we have more points after sign change, drop the highest point
                        --end;
                    }

                    // we need to do one more attempt
                    nextX = nan;

                }

            } while (nextX.isNaN() && (end - start > 1));

            if (nextX.isNaN()) {
                // fall back to bisection
                nextX = xA.add(xB.subtract(xA).divide(2));
                start = signChangeIndex - 1;
                end   = signChangeIndex;
            }

            // evaluate the function at the guessed root
            evaluations.incrementCount();
            final Dfp nextY = f.value(nextX);
            if (nextY.isZero()) {
                // we have found an exact root, since it is not an approximation
                // we don't need to bother about the allowed solutions setting
                return nextX;
            }

            if ((nbPoints > 2) && (end - start != nbPoints)) {

                // we have been forced to ignore some points to keep bracketing,
                // they are probably too far from the root, drop them from now on
                nbPoints = end - start;
                System.arraycopy(x, start, x, 0, nbPoints);
                System.arraycopy(y, start, y, 0, nbPoints);
                signChangeIndex -= start;

            } else  if (nbPoints == x.length) {

                // we have to drop one point in order to insert the new one
                nbPoints--;

                // keep the tightest bracketing interval as centered as possible
                if (signChangeIndex >= (x.length + 1) / 2) {
                    // we drop the lowest point, we have to shift the arrays and the index
                    System.arraycopy(x, 1, x, 0, nbPoints);
                    System.arraycopy(y, 1, y, 0, nbPoints);
                    --signChangeIndex;
                }

            }

            // insert the last computed point
            //(by construction, we know it lies inside the tightest bracketing interval)
            System.arraycopy(x, signChangeIndex, x, signChangeIndex + 1, nbPoints - signChangeIndex);
            x[signChangeIndex] = nextX;
            System.arraycopy(y, signChangeIndex, y, signChangeIndex + 1, nbPoints - signChangeIndex);
            y[signChangeIndex] = nextY;
            ++nbPoints;

            // update the bracketing interval
            if (nextY.multiply(yA).negativeOrNull()) {
                // the sign change occurs before the inserted point
                xB = nextX;
                yB = nextY;
                absYB = yB.abs();
                ++agingA;
                agingB = 0;
            } else {
                // the sign change occurs after the inserted point
                xA = nextX;
                yA = nextY;
                absYA = yA.abs();
                agingA = 0;
                ++agingB;

                // update the sign change index
                signChangeIndex++;

            }

        }

    }