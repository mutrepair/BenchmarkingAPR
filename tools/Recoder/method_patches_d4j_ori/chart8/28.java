    public Week(Date time, TimeZone zone) {
        // defer argument checking...
super.Week(zone);
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE, Locale.getDefault());    }