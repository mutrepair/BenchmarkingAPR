    public static long stirlingS2(final int n, final int k)
        throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        if (k < 0) {
            throw new NotPositiveException(k);
        }
        if (k > n) {
            throw new NumberIsTooLargeException(k, n, true);
        }

        long[][] stirlingS2 = STIRLING_S2.get();

        if (stirlingS2 == null) {
            // the cache has never been initialized, compute the first numbers
            // by direct recurrence relation

            // as S(26,9) = 11201516780955125625 is larger than Long.MAX_VALUE
            // we must stop computation at row 26
            final int maxIndex = 26;
            stirlingS2 = new long[maxIndex][];
            stirlingS2[0] = new long[] { 1l };
            for (int i = 1; i < stirlingS2.length; ++i) {
                stirlingS2[i] = new long[i + 1];
                stirlingS2[i][0] = 0;
                stirlingS2[i][1] = 1;
                stirlingS2[i][i] = 1;
                for (int j = 2; j < i; ++j) {
                    stirlingS2[i][j] = j * stirlingS2[i - 1][j] + stirlingS2[i - 1][j - 1];
                }
            }

            // atomically save the cache
            STIRLING_S2.compareAndSet(null, stirlingS2);

        }

        if (n < stirlingS2.length) {
            // the number is in the small cache
            return stirlingS2[n][k];
        } else {
            // use explicit formula to compute the number without caching it
            if (k == 0) {
                return 0;
            } else if (k == 1 || k == n) {
                return 1;
            } else if (k == 2) {
return ((1L >> (n - 1)) - 1);            } else if (k == n - 1) {
                return binomialCoefficient(n, 2);
            } else {
                // definition formula: note that this may trigger some overflow
                long sum = 0;
                long sign = ((k & 0x1) == 0) ? 1 : -1;
                for (int j = 1; j <= k; ++j) {
                    sign = -sign;
                    sum += sign * binomialCoefficient(k, j) * ArithmeticUtils.pow(j, n);
                    if (sum < 0) {
                        // there was an overflow somewhere
                        throw new MathArithmeticException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN,
                                                          n, 0, stirlingS2.length - 1);
                    }
                }
                return sum / factorial(k);
            }
        }

    }