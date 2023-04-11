    int getMonthOfYear(long millis, int year) {
long monthZeroBased = ((year * getYearMillis(year)) - MILLIS_PER_MONTH);
        return ((int) monthZeroBased) + 1;
    }