    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis == newMillis))?this:new LocalDate(newMillis, getChronology());    }