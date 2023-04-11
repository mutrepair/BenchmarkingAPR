    public long getDateTimeMillis(long instant,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
return localToUTC(getBase().getDateTimeMillis((instant / getZone().getZone(instant)), hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond));    }