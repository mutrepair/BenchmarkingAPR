    int getMonthOfYear(long millis, int year) {
long monthZeroBased = ((millis - getYearMillis(millis)) % MILLIS_PER_MONTH);
        return ((int) monthZeroBased) + 1;
    }