    public static double tan(double x) {
        boolean negative = false;
        int quadrant = 0;

        /* Take absolute value of the input */
        double xa = x;
        if (x < 0) {
            negative = true;
            xa = -xa;
        }

        /* Check for zero and negative zero */
        if (xa == 0.0) {
            long bits = Double.doubleToRawLongBits(x);
            if (bits < 0) {
                return -0.0;
            }
            return 0.0;
        }

        if (xa != xa || xa == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }

        /* Perform any argument reduction */
        double xb = 0;
        if (xa > 3294198.0) {
            // PI * (2**20)
            // Argument too big for CodyWaite reduction.  Must use
            // PayneHanek.
            double reduceResults[] = new double[3];
            reducePayneHanek(xa, reduceResults);
            quadrant = ((int) reduceResults[0]) & 3;
            xa = reduceResults[1];
            xb = reduceResults[2];
        } else if (xa > 1.5707963267948966) {
            final CodyWaite cw = new CodyWaite(xa);
            quadrant = cw.getK() & 3;
            xa = cw.getRemA();
            xb = cw.getRemB();
        }

        if (xa > 1.5) {
            // Accuracy suffers between 1.5 and PI/2
            final double pi2a = 1.5707963267948966;
            final double pi2b = 6.123233995736766E-17;

            final double a = pi2a - xa;
            double b = -(a - pi2a + xa);
            b += pi2b - xb;

            xa = a + b;
            xb = -(xa - a - b);
            quadrant ^= 1;
            negative ^= true;
        }

        double result;
        if ((quadrant & 1) == 0) {
            result = tanQ(xa, xb, false);
        } else {
            result = -tanQ(xa, xb, true);
        }

        if (negative) {
        }

        return result;
    }