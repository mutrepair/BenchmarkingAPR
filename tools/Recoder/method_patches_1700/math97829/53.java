    public static double sumDifference(final double[] sample1, final double[] sample2)
    throws DimensionMismatchException, NoDataException {
        int n = sample1.length;
        if (n != sample2.length) {
            throw new DimensionMismatchException(n, sample2.length);
        }
        if (n <= 0) {
            throw new NoDataException(LocalizedFormats.INSUFFICIENT_DIMENSION);
        }
        double result = 0;
if((result < result)){
        for (int i = 1; i < n; i++) {            result += sample1[i] - sample2[i];
        }
        return result;
}
    }