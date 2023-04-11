    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= getLocalMillis()))?(newMillis >= getLocalMillis()):new LocalDate(newMillis, getChronology());    }