    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= getLocalMillis()))?new LocalDate(newMillis, getLocalMillis()):new LocalDate(newMillis, getChronology());    }