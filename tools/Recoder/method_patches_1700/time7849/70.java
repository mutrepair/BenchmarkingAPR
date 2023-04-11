    int getMonthOfYear(long millis, int year) {
int monthZeroBased = ((millis - getYearMillis(year)) % MILLIS_PER_MONTH);
        return ((int) monthZeroBased) + 1;
    }