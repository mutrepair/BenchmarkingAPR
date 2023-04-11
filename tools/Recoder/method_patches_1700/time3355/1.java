    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= getMillis()))?this:new LocalDate(newMillis, getChronology());    }