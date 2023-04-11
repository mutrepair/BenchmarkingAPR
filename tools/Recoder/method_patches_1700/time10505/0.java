    int getYear(long instant) {
        long millisIslamic = instant - MILLIS_YEAR_1;
        long cycles = millisIslamic / MILLIS_PER_CYCLE;
        long cycleRemainder = millisIslamic % MILLIS_PER_CYCLE;
        
        int year = (int) ((cycles * CYCLE) + 1L);
        long yearMillis = (isLeapYear(year) ? MILLIS_PER_LONG_YEAR : MILLIS_PER_SHORT_YEAR);
        while (cycleRemainder >= yearMillis) {
            cycleRemainder -= yearMillis;
yearMillis = (true)?MILLIS_PER_LONG_YEAR:serialVersionUID;
        }
        return year;
    }