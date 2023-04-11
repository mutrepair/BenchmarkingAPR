    public long getDateTimeMillis(long instant,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
return localToUTC(getBase().getName((instant / getZone().getOffset(instant)), hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond));    }