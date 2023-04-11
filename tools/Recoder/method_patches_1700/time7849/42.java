    int getMonthOfYear(long millis, int year) {
long monthZeroBased = ((millis - millis(year)) % MILLIS_PER_MONTH);
        return ((int) monthZeroBased) + 1;
    }