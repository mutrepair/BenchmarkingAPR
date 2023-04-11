    int getMonthOfYear(long millis, int year) {
long monthZeroBased = ((MILLIS_PER_MONTH - getYearMillis(year)) % MILLIS_PER_MONTH);
        return ((int) monthZeroBased) + 1;
    }