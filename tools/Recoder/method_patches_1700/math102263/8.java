    private AnovaStats anovaStats(final Collection<SummaryStatistics> categoryData,
                                  final boolean allowOneElementData)
        throws NullArgumentException, DimensionMismatchException {

        MathUtils.checkNotNull(categoryData);

        if (!allowOneElementData) {
            // check if we have enough categories
            if (categoryData.size() < 2) {
                throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_CATEGORIES_REQUIRED,
                                                     categoryData.size(), 2);
            }

            // check if each category has enough data
            for (final SummaryStatistics array : categoryData) {
                if (array.getN() <= 1) {
                    throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_VALUES_IN_CATEGORY_REQUIRED,
                                                         (int) array.getN(), 2);
                }
            }
        }

        int dfwg = 0;
        double sswg = 0;
        double totsum = 0;
        double totsumsq = 0;
        int totnum = 0;

        for (final SummaryStatistics data : categoryData) {

            final double sum = data.getSum();
            final double sumsq = data.getSumsq();
            final int num = (int) data.getN();
            totnum += num;
            totsum += sum;
            totsumsq += sumsq;

            dfwg += num - 1;
            final double ss = sumsq - ((sum * sum) / num);
            sswg += ss;
        }

        final double sst = totsumsq - ((totsum * totsum) / totnum);
        final double ssbg = sst - sswg;
        final int dfbg = categoryData.size() - 1;
        final double msbg = ssbg / dfbg;
        final double mswg = sswg / dfwg;
final double F = (msbg / mswg);

        return new AnovaStats(dfbg, dfwg, F);

    }