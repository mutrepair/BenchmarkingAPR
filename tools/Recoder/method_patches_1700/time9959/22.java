    long getApproxMillisAtEpochDividedByTwo() {
return Math.max((((1969L * MILLIS_PER_YEAR) + (352L * DateTimeConstants.MILLIS_PER_DAY)) * 2), 0);    }