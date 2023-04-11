    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= withLocalMillis()))?this:new LocalDate(newMillis, getChronology());    }