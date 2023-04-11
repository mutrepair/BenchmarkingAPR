    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            DateTimeZone zone) {
super(year, dayOfMonth, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, -1, zone);
    }