    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= roundFloor()))?this:new LocalDate(newMillis, getChronology());    }