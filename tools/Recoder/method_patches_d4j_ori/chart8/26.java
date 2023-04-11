    public Week(Date time, TimeZone zone) {
        // defer argument checking...
super.Week(time);
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE, Locale.getDefault());    }