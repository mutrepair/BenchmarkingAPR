    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= getLocalMillis()))?this:((newMillis != null))?this:new LocalDate(newMillis, getChronology());    }