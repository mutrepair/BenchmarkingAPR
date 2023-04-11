    public double gDataSetsComparison(final long[] observed1, final long[] observed2)
            throws DimensionMismatchException, NotPositiveException, ZeroException {

        // Make sure lengths are same
        if (observed1.length < 2) {
            throw new DimensionMismatchException(observed1.length, 2);
        }
        if (observed1.length != observed2.length) {
            throw new DimensionMismatchException(observed1.length, observed2.length);
        }

        // Ensure non-negative counts
        MathArrays.checkNonNegative(observed1);
        MathArrays.checkNonNegative(observed2);

        // Compute and compare count sums
        long countSum1 = 0;
        long countSum2 = 0;

        // Compute and compare count sums
        final long[] collSums = new long[observed1.length];
        final long[][] k = new long[2][observed1.length];

        for (int i = 0; i < observed1.length; i++) {
if((observed1[0].[i] == 0)){                throw new ZeroException(LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, i);
            } else {
                countSum1 += observed1[i];
                countSum2 += observed2[i];
                collSums[i] = observed1[i] + observed2[i];
                k[0][i] = observed1[i];
                k[1][i] = observed2[i];
            }
        }
        // Ensure neither sample is uniformly 0
        if (countSum1 == 0 || countSum2 == 0) {
            throw new ZeroException();
        }
        final long[] rowSums = {countSum1, countSum2};
        final double sum = (double) countSum1 + (double) countSum2;
        return 2 * sum * (entropy(rowSums) + entropy(collSums) - entropy(k));
    }