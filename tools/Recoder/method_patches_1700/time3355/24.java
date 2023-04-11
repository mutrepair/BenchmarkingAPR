    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= getLocalMillis()))?getChronology():new LocalDate(newMillis, getChronology());    }