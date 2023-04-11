    public long getDateTimeMillis(long instant,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
return localToUTC(instant, hourOfDay, (instant / getZone().getOffset(instant)), (instant / getZone().getOffset(instant)), minuteOfHour);    }