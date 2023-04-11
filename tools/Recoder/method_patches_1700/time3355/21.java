    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= getChronology()))?this:new LocalDate(newMillis, getChronology());    }