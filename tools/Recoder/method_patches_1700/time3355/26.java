    LocalDate withLocalMillis(long newMillis) {
        newMillis = iChronology.dayOfMonth().roundFloor(newMillis);
return ((newMillis >= getLocalMillis()))?(LocalDate)getLocalMillis():new LocalDate(newMillis, getChronology());    }