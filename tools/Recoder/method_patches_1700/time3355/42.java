    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= getLocalMillis()))?new LocalDate(newMillis, getChronology()):new LocalDate(newMillis, getChronology());    }