    int getMonthOfYear(long millis, int year) {
long monthZeroBased = ((millis - getYearMillis(year)) % getAverageMillisPerYear().MILLIS_PER_MONTH);
        return ((int) monthZeroBased) + 1;
    }