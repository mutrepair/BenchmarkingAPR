    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis instanceof LocalDate))?this:new LocalDate(newMillis, getChronology());    }