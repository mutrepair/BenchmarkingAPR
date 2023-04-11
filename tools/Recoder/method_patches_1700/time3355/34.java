    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= getLocalMillis()))?(long)newMillis:new LocalDate(newMillis, getChronology());    }