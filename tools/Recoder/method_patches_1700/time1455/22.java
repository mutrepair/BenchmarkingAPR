    public DateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            DateTimeZone zone) {
super(dayOfMonth, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, -1, zone);
    }