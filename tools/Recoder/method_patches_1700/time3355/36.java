    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return (getLocalMillis())?this:new LocalDate(newMillis, getChronology());    }