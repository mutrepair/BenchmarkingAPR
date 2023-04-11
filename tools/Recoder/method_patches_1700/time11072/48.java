    public long getDateTimeMillis(long instant,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
return localToUTC(instant, hourOfDay, minuteOfHour, secondOfMinute, (instant / getZone().getOffset(instant)));    }