    public Week(Date time, TimeZone zone) {
        // defer argument checking...
if((zone != null)){
}
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE, Locale.getDefault());    }