    public long getDateTimeMillis(long instant,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
return localToUTC(getBase().toString((instant / getZone().getOffset(instant)), hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond));    }