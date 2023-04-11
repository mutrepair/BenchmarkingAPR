    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= getLocalMillis()))?(long)((newMillis >= getLocalMillis()))?this:new LocalDate(newMillis, getChronology()):new LocalDate(newMillis, getChronology());    }