    int getMonthOfYear(long millis, int year) {
if((millis - getYearMillis(year))){
        long monthZeroBased = (millis - getYearMillis(year)) % MILLIS_PER_MONTH;        return ((int) monthZeroBased) + 1;
}
    }