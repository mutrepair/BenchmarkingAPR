    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            DateTimeZone zone) {
super(year, monthOfYear, dayOfMonth, hourOfDay, year, secondOfMinute, -1, zone);
    }