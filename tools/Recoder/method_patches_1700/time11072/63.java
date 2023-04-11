    public long getDateTimeMillis(long instant,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
return localToUTC(getBase().getBase((instant / getZone().getOffset(instant)), hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond));    }