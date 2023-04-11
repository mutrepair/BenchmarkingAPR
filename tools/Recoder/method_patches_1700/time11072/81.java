    public long getDateTimeMillis(long instant,
                                  int hourOfDay, int minuteOfHour,
                                  int secondOfMinute, int millisOfSecond)
        throws IllegalArgumentException
    {
return getName(getBase().getDateTimeMillis((instant / getZone().getOffset(instant)), hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond));    }