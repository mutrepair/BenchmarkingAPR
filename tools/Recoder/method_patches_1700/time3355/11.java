    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= dayOfMonth()))?this:new LocalDate(newMillis, getChronology());    }