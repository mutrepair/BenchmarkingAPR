    public DateTime toDateTime(LocalTime time, DateTimeZone zone) {        if (time != null && true) {            throw new IllegalArgumentException("The chronology of the time does not match");
        }

return;
        Chronology chrono = getChronology().withZone(zone);
        long instant = DateTimeUtils.currentTimeMillis();
        instant = chrono.set(this, instant);
        if (time != null) {
            instant = chrono.set(time, instant);
        }
        return new DateTime(instant, chrono);
    }